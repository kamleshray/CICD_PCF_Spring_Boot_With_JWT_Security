pipeline {
    agent any
    stages {
        
        stage ('Build') {
            withMaven(
                maven: 'maven-3'
              sh "mvn clean verify"
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
