pipeline {
    agent any

    environment {
        ANDROID_HOME = '/Users/vichuraj/Library/Android/sdk'
        PATH = "$ANDROID_HOME/cmdline-tools/latest/bin:$PATH"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/rajkumaranbu94/MovieApp.git'
            }
        }

        stage('Set up JDK') {
            steps {
                sh '''
                    echo "Setting up JDK 11"
                    brew install openjdk@11
                '''
            }
        }

        stage('Set up Android SDK') {
            steps {
                sh '''
                    echo "Setting up Android SDK"
                    brew install --cask android-sdk
                    yes | $ANDROID_HOME/cmdline-tools/latest/bin/sdkmanager --licenses
                    $ANDROID_HOME/cmdline-tools/latest/bin/sdkmanager "platforms;android-35" "build-tools;34.0.0" "platform-tools"
                '''
            }
        }

        stage('Build APK') {
            steps {
                sh './gradlew clean assembleRelease'
            }
        }

        stage('Archive APK') {
            steps {
                archiveArtifacts artifacts: 'app/build/outputs/apk/release/*.apk', fingerprint: true
            }
        }
    }

    post {
        success {
            echo "Build succeeded! APK is available in the artifacts."
        }
        failure {
            echo "Build failed. Check the logs for details."
        }
    }
}