pipeline {
    agent any
    stages {
        
        stage ('Build') {
            
            withMaven(
                maven: 'maven-3'
                mavenSettingsConfig: 'my-maven-settings') {
              // Run the maven build
              sh "mvn clean verify"

            } // withMaven will discover the generated Maven artifacts, JUnit Surefire & FailSafe & FindBugs & SpotBugs reports...
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
