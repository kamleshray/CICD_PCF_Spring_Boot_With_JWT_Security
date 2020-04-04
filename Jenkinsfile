pipeline {
    agent any
    stages {
        
        stage ('Build') {
            steps {
                    bat 'mvn clean package'
            }
        }
        stage ('Deploy') {
            steps {

                withCredentials([[$class          : 'UsernamePasswordMultiBinding',
                                  credentialsId   : 'PCF_LOGIN',
                                  usernameVariable: 'USERNAME',
                                  passwordVariable: 'PASSWORD']]) {

                    bat 'cf login -a http://api.run.pivotal.io -u %USERNAME% -p %PASSWORD%'
                    bat 'cf push'
                }
            }

        }

    }

}
