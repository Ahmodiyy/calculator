pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/Ahmodiyy/calculator.git', branch: 'main'
            }
        }
        stage('Linux permission') {
            steps {
                sh "chmod +x gradlew"
            }
        }
        stage('docker build') {
                     steps {
                         sh "./gradlew build"
                     }
                }
        stage("Code Coverage") {
              steps {
                  sh "./gradlew jacocoTestReport"
                  publishHTML(target: [
                                      allowMissing: false,
                                      alwaysLinkToLastBuild: false,
                                      keepAll: true,
                                      reportDir: 'build/reports/jacoco/test/html',
                                      reportFiles: 'index.html',
                                      reportName: 'JaCoCo Report'
                                  ])
                  sh "./gradlew jacocoTestCoverageVerification"
              }
        }
        stage("Static code analysis") {
               steps {
                   sh "./gradlew checkstyleMain"
                   publishHTML(target: [
                                      allowMissing: false,
                                      alwaysLinkToLastBuild: false,
                                      keepAll: true,
                                      reportDir: 'build/reports/checkstyle',
                                      reportFiles: 'main.html',
                                      reportName: 'Checkstyle Report'
                                  ])
               }
        }
        stage('docker build') {
                 steps {
                     sh "docker build -t calculator ."
                 }
        }

    }
    post {
            always {
                mail to: 'ahmodolaitan03@gmail.com',
                    subject: "Completed Pipeline for: ${currentBuild.fullDisplayName}",
                    body: "Your build completed, please check: ${env.BUILD_URL}"
                slackSend channel: '#test', color: 'green', message: "The pipeline ${currentBuild.fullDisplayName} result."
            }
        }
}
