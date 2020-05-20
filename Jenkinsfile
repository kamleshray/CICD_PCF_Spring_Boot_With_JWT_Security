pipeline {
    agent any
    
    stages {
        
        stage('Initialize'){
            steps {
                def dockerHome = tool 'MyDocker'
                def mavenHome  = tool 'MyMaven'
                env.PATH = "${dockerHome}/bin:${mavenHome}/bin:${env.PATH}"
            }
        }
        
        stage ('Build') {
            steps {
                    sh 'mvn clean package'
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
