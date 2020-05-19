pipeline {
    agent any
    stages {
        
        stage ('Build') {
            steps {
                   mvnHome = tool name: 'maven', type: 'maven'
                   sh 'mvn clean package'
                
                mvnHome = tool name: 'Apache Maven 3.6.0', type: 'maven'
                sh "${mvnHome}/bin/mvn clean install"
            }
        }
        stage ('Deploy') {
            steps {

                withCredentials([[$class          : 'UsernamePasswordMultiBinding',
                                  credentialsId   : 'PCF_LOGIN',
                                  usernameVariable: 'USERNAME',
                                  passwordVariable: 'PASSWORD']]) {

                    sh 'cf login -a http://api.run.pivotal.io -u %USERNAME% -p %PASSWORD%'
                    sh 'cf push'

                }
            }

        }

    }

}
