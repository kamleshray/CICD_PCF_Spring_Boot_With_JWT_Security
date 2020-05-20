

node{
    stage('build'){
        withMaven(maven: 'mvn') {
            sh "mvn clean package"
        }
    }
}
