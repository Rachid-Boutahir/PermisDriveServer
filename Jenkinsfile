pipeline {
    agent any
    tools{
        maven 'maven'
        git 'git'
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'todo'
            }
        }

        stage('Build') {
            steps {
                script {
                    if (isUnix()) {
                        sh 'mvn clean install'
                    } else {
                        bat 'mvn clean install'
                    }
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    if (isUnix()) {
                        sh 'mvn test'
                    } else {
                        bat 'mvn test'
                    }
                }
            }
        }
    }
}
