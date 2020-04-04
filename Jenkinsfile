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
                                  credentialsId   : '3034c7fb-79b8-4698-a017-9991b9206deb',
                                  usernameVariable: 'USERNAME',
                                  passwordVariable: 'PASSWORD']]) {

                    bat 'cf login -a http://api.run.pivotal.io -u ${USERNAME} -p ${PASSWORD}'
                    bat 'cf push'
                }
            }

        }

    }

}
