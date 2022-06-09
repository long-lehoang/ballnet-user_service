pipeline {
    agent { docker { image 'maven:3.8.3-openjdk-17' } }
    stages {
        stage('test') {
            steps {
                sh 'mvn --version'
                sh 'mvn clean test'
            }
        }
        stage('build') {
            steps {
                sh 'mvn --version'
                sh 'mvn clean install'
            }
        }
    }
}