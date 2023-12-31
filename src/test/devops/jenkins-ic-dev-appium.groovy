pipeline {

    agent any

    environment {
        emulator1portF = 5556
        emulator1portR = 5557
        avd1 = 'x86_64_pixel_xl_api_32'
        numberOfTests = 3
        
        PATH = "/usr/local/Cellar/node/21.2.0/bin/:${env.PATH}"        
    }

    tools { 
        maven 'Main Maven' 
        jdk 'Android Studio Java Home'
    }

    options { timestamps () }

    stages {
        stage('SCM') {
            steps {
                deleteDir()
                println('*** CHECK OUT CODE FROM GIT REPO ***')
                checkout scm
            }
        }

        stage('Build Tests') {
            steps {
                script {
                    // This assembles a debug apk with the android UI test inside it
                    sh "mvn clean compile"
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
                            
                            if (currentActiveDevices.isEmpty() || currentActiveDevices.trim().isEmpty()) {
                                echo "Emulator startup - Restarting emulator"
                                try {
                                    // Removed. Seems to crash emulator > -gpu swiftshader_indirect
                                    sh "$ANDROID_HOME/tools/emulator -skin 1440x3120 -ports ${env.emulator1portF},${env.emulator1portR} -avd ${env.avd1} -no-audio -no-snapshot-load -no-snapshot-save -wipe-data -no-boot-anim -memory 2048 -cache-size 1000 -verbose"
                                } catch (Exception e) {
                                    echo 'Emulator startup - Exception occurred: ' + e.toString()
                                }
                            }
                         }
                     }
                }
                
                stage('Appium server startup') {
                    steps {
                        script {
                            try {
                                sh "node /usr/local/lib/node_modules/appium/index.js"
                                echo "Appium server startup - Started on background"
                            } catch (Exception e) {
                                echo 'Appium server startup - Exception occurred: ' + e.toString()
                            }
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

                                    while (response != "1") {
                                        try {
                                            if (numberOfRetries > maxRetries) {
                                                break
                                            }

                                            response = sh(script: "$ANDROID_HOME/platform-tools/adb -s emulator-${env.emulator1portF} wait-for-device shell getprop sys.boot_completed", returnStdout: true).trim()
                                            echo "adb shell getprop sys.boot_completed respond with: ${response}"
                                            sleep(time: 45, unit: 'SECONDS')
                                            echo "Waiting for emulator to finishing starting up"
                                        } catch (Exception e) {
                                            echo 'Exception occurred: ' + e.toString()
                                            numberOfRetries = numberOfRetries + 1
                                            sleep(time: 45, unit: 'SECONDS')
                                        }
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
                                    def closeParallelServices = {
                                        echo "Run UI tests - Shutting down Emulator"
                                        sh "$ANDROID_HOME/platform-tools/adb kill-server"
                                        sh "$ANDROID_HOME/platform-tools/adb -s emulator-${env.emulator1portF} emu kill"
                                    }

                                    echo "Run UI tests - Beginning appium tests runs"
                                    
                                    try {
                                        def appiumTestRun = sh(script: "mvn clean test -DPlatform=android", returnStdout: true)
                                    
                                        // should you use appium from a server not run by jenkins check: "Tests run: ${env.numberOfTests}, Failures: 0, Errors: 0, Skipped: 0"
                                        // else
                                        if (appiumTestRun.contains("Tests run: ${env.numberOfTests}, Failures: 0, Errors: 0, Skipped: 0") == false) {
                                            closeParallelServices()
                                            error("Run UI tests - Build failed because some tests failed, had errors or where skipped")
                                        } else {
                                            closeParallelServices()
                                        }
                                        echo "Run UI tests - Appium tests completed"
                                    } catch (Exception e) {
                                        closeParallelServices()
                                        echo "Run UI tests - Appium tests failed due to error"
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}