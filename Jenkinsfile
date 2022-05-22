pipeline {
    agent any

    stages {
        stage('Clone'){
            steps {
                echo 'Cloning.. And setting up voulumes..'
                sh 'docker system prune --all --volumes -f'
                sh 'docker volume create vol-in'
                sh 'docker volume create vol-out'
                sh 'docker build -t cloner:latest . -f /var/jenkins_home/workspace/DevOpsPipeline/docker-clone'
                sh 'docker run --mount source=vol-in,destination=/inputVol cloner:latest'
            }

        }

        stage('Build') {
            steps {
                echo 'Building..'
                sh 'docker build -t builder:latest . -f /var/jenkins_home/workspace/DevOpsPipeline/docker-build'
                sh 'docker run --mount source=vol-in,destination=/inputVol --mount source=vol-out,destination=/outputVol builder:latest'

            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
                sh 'docker build -t tester:latest . -f /var/jenkins_home/workspace/DevOpsPipeline/docker-test'
                sh 'docker run --mount source=vol-in,destination=/inputVol --mount source=vol-out,destination=/outputVol tester:latest'

            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
                sh 'docker build -t deploy:latest . -f /var/jenkins_home/workspace/DevOpsPipeline/docker-deploy'
                sh 'docker run --mount source=vol-out,destination=/outputVol deploy:latest'
            }
        }
    }
}
