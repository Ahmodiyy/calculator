pipeline {
    agent {
    label "pipeline"
    }
    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/Ahmodiyy/calculator.git', branch: 'main'
            }
        }
        stage('Linux permission') {
            steps {
                //sh "chmod +x gradlew"
                //sh "docker version"
                bat "docker version"
            }
        }
        stage('gradle build') {
                     steps {
                         //sh "./gradlew clean"
                         //sh "./gradlew build"
                         bat "./gradlew clean"
                         bat "./gradlew build"
                     }
                }
        stage("Code Coverage") {
              steps {
                  //sh "./gradlew jacocoTestReport"
                  bat "./gradlew jacocoTestReport"
                  publishHTML(target: [
                                      allowMissing: false,
                                      alwaysLinkToLastBuild: false,
                                      keepAll: true,
                                      reportDir: 'build/reports/jacoco/test/html',
                                      reportFiles: 'index.html',
                                      reportName: 'JaCoCo Report'
                                  ])
                  //sh "./gradlew jacocoTestCoverageVerification"
                  bat "./gradlew jacocoTestCoverageVerification"
              }
        }
        stage("Static code analysis") {
               steps {
                   //sh "./gradlew checkstyleMain"
                   bat "./gradlew checkstyleMain"
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
                     //sh "docker build -t calculator ."
                     bat "docker build -t ahmodiyy/calculator ."
                 }
        }
//         stage("Docker login") {
//           steps {
//             withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'dockerhub',
//                               usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
//               sh "docker login --username $USERNAME --password $PASSWORD"
//             }
//           }
//         }

        stage('docker push') {
                 steps {
                     //sh "docker push calculator"
                     bat "docker push ahmodiyy/calculator"
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
