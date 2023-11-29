pipeline {

    agent any

    environment {
        emulator1 = 5556
    }

    tools { 
        maven 'MAVEN_HOME' 
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
        stage('Build executables') {
            steps {
                script {
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
                            sh "adb kill-server"
                            sh "adb start-server"

                            def adbDevicesResponse = sh(script: "adb devices", returnStdout: true)
                            echo adbDevicesResponse
                            def currentActiveDevices = adbDevicesResponse.minus("List of devices attached")
                            echo currentActiveDevices
                            if(currentActiveDevices.isEmpty() || currentActiveDevices.trim().isEmpty()) {
                                echo "Restarting emulator"
                                try {
                                    // Removed. Seems to crash emulator > -gpu swiftshader_indirect
                                    sh "/usr/lib/android-sdk/emulator/emulator -skin 1440x2560 -ports ${env.emulator1},${env.emulator1+1} -avd x86_64_pixel_xl_api_32 -no-window -no-audio -no-snapshot-load -no-snapshot-save -accel off -wipe-data -no-boot-anim -memory 2048 -cache-size 1000 -partition-size 2048 -verbose"
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
                            sh "appium"
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
                                    while (response != "1") {
                                        response = sh(script: "adb -s emulator-${env.emulator1} wait-for-device shell getprop sys.boot_completed", returnStdout: true).trim()
                                        echo "adb shell getprop sys.boot_completed, response was: ${response}"
                                        sleep(time: 60, unit: 'SECONDS')
                                        echo "Waiting for emulator to finishing starting up"
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