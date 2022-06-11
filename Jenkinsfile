pipeline {
    agent any
    tools{
        maven 'maven-3.8.5'
        jdk 'jdk-17'
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