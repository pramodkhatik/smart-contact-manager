pipeline {
	agent any
	stages{
	    stage('Prepare Workspace') {
            steps {
                cleanWs()
            }
        }
		stage('Git clone'){
			steps{
				checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[credentialsId: '91e8f855-3164-4dcb-92d5-f4e0102ded18', url: 'https://github.com/pramodkhatik/smart-contact-manager.git']])
			}
		}
		stage('Build docker image'){
			steps{
				script{
					sh 'docker build --pull --no-cache -t pramodkhatik/scm-backend-vagrant .'
				}
			}
		}
		stage('Push image to Dockerhub'){
			steps{
				script{
				withCredentials([string(credentialsId: 'DockerHub', variable: 'DockerPwd')]) {
				sh 'docker  login -u pramodkhatik -p ${DockerPwd}'
				}
				sh 'docker push pramodkhatik/scm-backend-vagrant'
				}
			}
		}
	}
}
