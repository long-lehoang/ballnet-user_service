pipeline {
    agent {
        docker {
            label 'docker-agent'
            image 'maven:3.8.3-openjdk-17'
        }
    }
    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                sh 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
                sh 'mvn test'
            }
        }
    }
}