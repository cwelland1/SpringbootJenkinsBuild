pipeline {
    agent any 
    stages {
        stage('Compile and Build') { 
            steps {
                sh "mvn clean compile"
            }
        }
       

        stage('Deploy to Dev') { 
            steps {
                sh "mvn package"
                sh 'docker build -t  icatdocker/docker_jenkins_springboot:${BUILD_NUMBER} .'
                  withCredentials([string(credentialsId: 'Dockerid', variable: 'Dockerpwd')]) {
                sh "docker login -u icatdocker -p ${Dockerpwd}"
                sh 'docker push icatdocker/docker_jenkins_springboot:${BUILD_NUMBER}'
                sh 'docker rm -f $(docker ps -a -q)'
                sh 'docker run -itd -p  8081:8080 icatdocker/docker_jenkins_springboot:${BUILD_NUMBER}'
            }
        }
    }

        stage('Functional Testing'){
            steps {
               echo "test"
                sleep 4
            }
        }

           stage(' Security Testing'){
            steps {
                echo "test"
                sleep 5
            }
        }
        
        stage('Deploy to UAT'){
            steps {
             echo "test"
            }
        }

        
        stage('Archving') { 
            steps {
                 archiveArtifacts '**/target/*.jar'
            }
        }
    }
}

