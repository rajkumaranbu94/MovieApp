pipeline {
    agent any

    environment {
        // Set the ANDROID_HOME path to where you have installed the SDK
        ANDROID_HOME = '/Users/vichuraj/Library/Android/sdk'

        // Update PATH to include the SDK tools and platform tools
        PATH = "$ANDROID_HOME/tools:$ANDROID_HOME/tools/bin:$ANDROID_HOME/platform-tools:$PATH"
    }

    stages {
        stage('Checkout') {
            steps {
                // Checkout the code from the repository
                git branch: 'main', url: 'https://github.com/rajkumaranbu94/MovieApp.git'
            }
        }

        stage('Set up Android SDK') {
            steps {
                script {
                    // Accept licenses for SDK components
                    sh 'yes | sdkmanager --licenses'
                    // Install the required SDK platforms and build tools
                    sh 'sdkmanager "platforms;android-30" "build-tools;30.0.3"'
                }
            }
        }

        stage('Build APK') {
            steps {
                script {
                    // Clean and build the APK
                    sh './gradlew clean assembleRelease'
                }
            }
        }

        stage('Run Unit Tests') {
            steps {
                script {
                    // Run unit tests
                    sh './gradlew test'
                }
            }
        }

        stage('Archive APK') {
            steps {
                script {
                    // Archive the APK file
                    archiveArtifacts allowEmptyArchive: true, artifacts: 'app/build/outputs/apk/release/*.apk'
                }
            }
        }
    }

    post {
        always {
            // Clean up any temporary files, if necessary
            cleanWs()
        }
    }
}
