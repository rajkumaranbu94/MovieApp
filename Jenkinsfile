pipeline {
    agent any

    environment {
        ANDROID_HOME = '/opt/android-sdk-linux'
        GRADLE_HOME = '/opt/gradle'
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
                    sh 'yes | sdkmanager --licenses'
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
