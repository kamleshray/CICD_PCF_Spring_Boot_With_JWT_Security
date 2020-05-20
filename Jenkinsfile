node {
    stage('Initialize'){
        def mavenHome  = tool 'MyMaven'
        env.PATH = "${mavenHome}/bin:${env.PATH}"
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

                    sh 'cf login -a https://api.run.pivotal.io -u $USERNAME -p $PASSWORD'
                    sh 'cf push'

                }
    }
}
