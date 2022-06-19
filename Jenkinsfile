pipeline {
    agent none
    stages {
        stage('Test') {
            agent {
                docker {
                    label 'docker-agent'
                    image 'maven:3.8.3-openjdk-17'
                }
            }
            steps {
                echo 'Testing..'
                sh 'mvn test'
            }
        }
        stage('Build') {
            agent {
                docker {
                    label 'docker-agent'
                    image 'maven:3.8.3-openjdk-17'
                }
            }
            steps {
                echo 'Building..'
                sh 'mvn package'
            }
        }
    }
}