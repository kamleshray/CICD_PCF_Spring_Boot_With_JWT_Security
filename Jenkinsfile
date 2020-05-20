node {
    stage('Initialize'){
        def dockerHome = tool 'MyDocker'
        def mavenHome  = tool 'MyMaven'
        env.PATH = "${dockerHome}/bin:${mavenHome}/bin:${env.PATH}"
    }
    stage('Checkout'){
        checkout scm
    }
    stage('Build'){
            bat 'mvn clean install'  
    }
    stage('Deploy') {
                withCredentials([[$class          : 'UsernamePasswordMultiBinding',
                                  credentialsId   : 'PCF_LOGIN',
                                  usernameVariable: 'USERNAME',
                                  passwordVariable: 'PASSWORD']]) {

                    bat 'cf login -a http://api.run.pivotal.io -u %USERNAME% -p %PASSWORD%'
                    bat 'cf push'

                }
    }
}
