pipeline {
    agent any 
    stages {
        stage('Compile and Clean') { 
            steps {
                sh "mvn clean compile"
            }
        }
       

        stage('Deploy to Dev') { 
            steps {
                sh "mvn package"
            }
        }


        stage('Testing'){
            steps {
                sh 'docker build -t  icatdocker/docker_jenkins_springboot:${BUILD_NUMBER} .'
            }
        }

        stage('Deploy to UAT'){
            
            steps {
                 withCredentials([string(credentialsId: 'Dockerid', variable: 'Dockerpwd')]) {
                    sh "docker login -u icatdocker -p ${Dockerpwd}"
                }
            }                
        }

        stage('Functional Testing'){
            steps {
                sh 'docker push icatdocker/docker_jenkins_springboot:${BUILD_NUMBER}'
            }
        }
        
        stage('Deploy to Prod'){
            steps {
               sh 'docker rm -f $(docker ps -a -q)'
                sh 'docker run -itd -p  8081:8080 icatdocker/docker_jenkins_springboot:${BUILD_NUMBER}'
            }
        }

        
        stage('Archving') { 
            steps {
                 archiveArtifacts '**/target/*.jar'
            }
        }
    }
}

