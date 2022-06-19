pipeline {
    agent none
    stages {
        stage('Test') {
            agent {
                docker {
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