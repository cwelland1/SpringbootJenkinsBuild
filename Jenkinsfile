pipeline {
    agent any 
    stages {
        stage('Compile and Build  (Maven)') { 
            steps {
                bat "mvn clean compile"
            }
        }
       

        stage('Deploy to Dev   (Docker)') { 
            steps {
                bat "mvn package"
                bat "docker build -t  icatdocker/docker_jenkins_springboot:${BUILD_NUMBER} ."
                  withCredentials([string(credentialsId: 'Dockerid', variable: 'Dockerpwd')]) {
                bat "docker login -u icatdocker -p ${Dockerpwd}"
                bat "docker push icatdocker/docker_jenkins_springboot:${BUILD_NUMBER}"
	        bat "docker rm -f jenkinscidev"
                bat "docker run --name jenkinscidev -itd -p 8082:8080 icatdocker/docker_jenkins_springboot:${BUILD_NUMBER} nginx ."
            }
        }
    }

 	 stage('Functional Testing   (Selenium)'){
	  steps {
	         echo "test"
                 git 'https://github.com/simitaws/Selenium-Course.git'
	         script {
		        bat(/mvn clean test/)
			    }
	            }
	        }

           stage(' Security Testing (SonarQube)'){
steps {
	git 'https://github.com/cwelland1/SpringbootJenkinsBuild.git'
	
withSonarQubeEnv('SonarQube') {
bat(/mvn sonar:sonar -Dsonar.login=cfb1a2c3b5624f88fc689d4027cb1e564f463039/)
echo "test"
}
}
}
        
        stage('Deploy to UAT  (Docker)'){
            steps {
             echo "UAT Deployment in Progress"
//		bat "mvn package"
//                bat "docker build -t  icatdocker/docker_jenkins_springboot:${BUILD_NUMBER} ."
//                  withCredentials([string(credentialsId: 'Dockerid', variable: 'Dockerpwd')]) {
//                bat "docker login -u icatdocker -p ${Dockerpwd}"
//                bat "docker push icatdocker/docker_jenkins_springboot:${BUILD_NUMBER}"
	        bat "docker rm -f jenkinsciuat"
                bat "docker run --name jenkinsciuat -itd -p 8083:8080 icatdocker/docker_jenkins_springboot:${BUILD_NUMBER} nginx ."
//            }
            }	
        }
	            stage('Email Notofication to Release Manager') { 
          steps {
          mail bcc: '', body: '''Hello,
Please     approve / reject CI/CD Pipeline.''', cc: 'chris.welland@icatalystinc.com', from: '', replyTo: '', subject: 'Pl Approve / Reject the release in CI/CD Pipeline', to: 'simit@icatalystinc.com'
            }
        }
	    stage('Approval by Release Manager'){
	      input {
			message "Proceed (If ERB Decision Memo Approved Go Live)"
		}
            steps {
		    sleep 2
	    }
	    }
      stage('Deploy to Production  (Docker)'){
	    //  input {
		//	message "Release Manager-Do you want to Proceed or Abort the deployment"
		//}
            steps {
             echo "Prod Deployment in Progress"
//		bat "mvn package"
//                bat "docker build -t  icatdocker/docker_jenkins_springboot:${BUILD_NUMBER} ."
//                  withCredentials([string(credentialsId: 'Dockerid', variable: 'Dockerpwd')]) {
//                bat "docker login -u icatdocker -p ${Dockerpwd}"
//                bat "docker push icatdocker/docker_jenkins_springboot:${BUILD_NUMBER}"
	        bat "docker rm -f jenkinsciprod"
                bat "docker run --name jenkinsciprod -itd -p 8084:8080 icatdocker/docker_jenkins_springboot:${BUILD_NUMBER} nginx ."
//            }
            }
	     post {
        always {
            emailext body: 'A Test EMail', recipientProviders: [[$class: 'DevelopersRecipientProvider'], [$class: 'RequesterRecipientProvider']], subject: 'Test'
        }
    }
      }   
        stage('Archiving') { 
            steps {
//                 archiveArtifacts '**/target/*.jar'
		    sleep 2
            }
        }
    }
}
