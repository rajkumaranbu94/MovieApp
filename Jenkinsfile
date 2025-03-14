pipeline {
    agent any

    environment {
        // Set environment variables
        ANDROID_HOME = '/path/to/android-sdk' // Update this path
        PATH = "$ANDROID_HOME/cmdline-tools/latest/bin:$PATH"
    }

    stages {
        // Stage 1: Checkout the code
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/rajkumaranbu94/MovieApp.git'
            }
        }

        // Stage 2: Set up JDK
        stage('Set up JDK') {
            steps {
                sh '''
                    echo "Setting up JDK 11"
                    sudo apt-get install -y openjdk-11-jdk
                '''
            }
        }

        // Stage 3: Set up Android SDK
        stage('Set up Android SDK') {
            steps {
                sh '''
                    echo "Setting up Android SDK"
                    sudo apt-get install -y wget unzip
                    wget https://dl.google.com/android/repository/commandlinetools-linux-6858069_latest.zip
                    unzip commandlinetools-linux-6858069_latest.zip -d $ANDROID_HOME
                    mkdir -p $ANDROID_HOME/cmdline-tools/latest
                    mv $ANDROID_HOME/cmdline-tools/* $ANDROID_HOME/cmdline-tools/latest/
                    yes | $ANDROID_HOME/cmdline-tools/latest/bin/sdkmanager --licenses
                    $ANDROID_HOME/cmdline-tools/latest/bin/sdkmanager "platforms;android-35" "build-tools;34.0.0" "platform-tools"
                '''
            }
        }

        // Stage 4: Build the APK
        stage('Build APK') {
            steps {
                sh './gradlew clean assembleRelease'
            }
        }

        // Stage 5: Archive the APK
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