pipeline {
    agent any

    stages {
        stage('Clone'){
            steps {
                echo 'Cloning.. And setting up voulumes..'

                sh 'docker volume create vol-in'
                sh 'docker volume create vol-out'

                sh 'docker build -t cloner:latest . -f /var/jenkins_home/workspace/DevOpsPipeline/Docker-clone'
                sh 'docker run --mount source=vol-in,destination=/inputVol cloner:latest'
            }

        }

        stage('Build') {
            steps {
                echo 'Building..'
                sh 'docker build -t builder:latest . -f /var/jenkins_home/workspace/DevOpsPipeline/Docker-build'
                sh 'docker run --mount source=vol-in,destination=/inputVol --mount source=vol-out,destination=/outputVol builder:latest'

            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
                sh 'docker build -t tester:latest . -f /var/jenkins_home/workspace/DevOpsPipeline/Docker-test'
                sh 'docker run --mount source=vol-in,destination=/inputVol --mount source=vol-out,destination=/outputVol tester:latest'

            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
