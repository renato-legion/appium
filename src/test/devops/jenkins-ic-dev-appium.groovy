pipeline {

    agent any

    environment {
        emulator1 = 5556
        avd1 = 'API_32_16cm_Pixel_7_Pro_UI_tests_enabled'
    }

    tools { 
        maven 'Main Maven' 
        jdk 'Android Studio Java Home'
    }

    stages {
        stage('SCM') {
            steps {
                deleteDir()
                println('*** CHECK OUT CODE FROM GIT REPO ***')
                checkout scm
            }
        }
        stage('Build') {
            steps {
                script {
                    //appium '/usr/local/bin/appium'

                    // This assembles a debug apk with the android UI test inside it
                    sh "mvn clean compile"
                    // sh "./gradlew assembleDebug assembleDebugAndroidTest installDebug installDebugAndroidTest"
                }
            }
        }
        stage('Appium UI test execution') {
            parallel {
                stage('Emulator startup') {
                    steps {
                        script {
                            sh "$ANDROID_HOME/platform-tools/adb kill-server"
                            sh "$ANDROID_HOME/platform-tools/adb start-server"

                            def adbDevicesResponse = sh(script: "$ANDROID_HOME/platform-tools/adb devices", returnStdout: true)
                            echo adbDevicesResponse
                            def currentActiveDevices = adbDevicesResponse.minus("List of devices attached")
                            echo currentActiveDevices
                            if(currentActiveDevices.isEmpty() || currentActiveDevices.trim().isEmpty()) {
                                echo "Restarting emulator"
                                try {
                                    // Removed. Seems to crash emulator > -gpu swiftshader_indirect
                                    sh "$ANDROID_HOME/tools/emulator -skin 1440x3120 -ports ${env.emulator1},${env.emulator1+1} -avd ${env.avd1} -no-audio -no-snapshot-load -no-snapshot-save -wipe-data -no-boot-anim -memory 2048 -cache-size 1000 -verbose"
                                } catch (Exception e) {
                                    echo 'Exception occurred: ' + e.toString()
                                }
                            }
                        }
                    }
                }
                stage('Appium server startup') {
                    steps {
                        script {
                            sh "#!/bin/bash /usr/local/lib/node_modules/appium/index.js"
                        }
                    }
                }
                stage('Appium UI Test') {
                    stages {
                        stage('Emulator condition verification') {
                            steps {
                                script {
                                    echo "Starting up emulator"
                                    def response = ""
                                    def maxRetries = 20
                                    def numberOfRetries = 0
                                    try {
                                        while (response != "1") {
                                            if (numberOfRetries > maxRetries) {
                                                break
                                            }

                                            response = sh(script: "$ANDROID_HOME/platform-tools/adb -s emulator-${env.emulator1} wait-for-device shell getprop sys.boot_completed", returnStdout: true).trim()
                                            echo "$ANDROID_HOME/platform-tools/adb shell getprop sys.boot_completed, response was: ${response}"
                                            sleep(time: 60, unit: 'SECONDS')
                                            echo "Waiting for emulator to finishing starting up"
                                        }
                                    } catch (Exception e) {
                                        echo 'Exception occurred: ' + e.toString()
                                        numberOfRetries = numberOfRetries + 1
                                        sleep(time: 120, unit: 'SECONDS')
                                    }

                                    if (numberOfRetries > maxRetries) {
                                        error("Build failed because emulator failed to start up correctly")
                                    }

                                    sleep(time: 60, unit: 'SECONDS')
                                    echo "Emulator finished booting necessary services"
                                }
                            }
                        }
                        stage('Run UI tests') {
                            steps {
                                script {
                                    echo "Beginning appium tests runs"
                                    def appiumTestRun = sh(script: "mvn clean test -DPlatform=android", returnStdout: true)
                                    echo "Appium tests completed"
                                    echo appiumTestRun
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}