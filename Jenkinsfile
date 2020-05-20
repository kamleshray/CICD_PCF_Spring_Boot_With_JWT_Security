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
            sh 'mvn clean install'  
    }
    stage('Deploy') {
                withCredentials([[$class          : 'UsernamePasswordMultiBinding',
                                  credentialsId   : 'PCF_LOGIN',
                                  usernameVariable: 'USERNAME',
                                  passwordVariable: 'PASSWORD']]) {

                    sh 'cf login -a http://api.run.pivotal.io -u %USERNAME% -p %PASSWORD%'
                    sh 'cf push'

                }
    }
}
