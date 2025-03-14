pipeline {
    agent any

    environment {
        // Set the path to the existing JDK installation
        JAVA_HOME = '/Users/vichuraj/Library/Java/JavaVirtualMachines/openjdk-23.0.1/Contents/Home'

        // Set the path to the existing Android SDK installation
        ANDROID_HOME = '/Users/vichuraj/Library/Android/sdk'

        // Combine all PATH updates into a single definition
        PATH = "$JAVA_HOME/bin:$ANDROID_HOME/cmdline-tools/latest/bin:$ANDROID_HOME/platform-tools:$PATH"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/rajkumaranbu94/MovieApp.git'
            }
        }

        stage('Set up Environment') {
            steps {
                sh '''
                    echo "Using existing JDK and Android SDK"
                    echo "JAVA_HOME: $JAVA_HOME"
                    echo "ANDROID_HOME: $ANDROID_HOME"
                    echo "PATH: $PATH"
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