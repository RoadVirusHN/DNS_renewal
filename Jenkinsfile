pipeline {
    agent {dns}

    stages {
        stage("pull stage") {
            echo "--pull stage start--";
            cd ~/DNS_renewal/;
            git pull origin main;
        }
        stage("delete old stage") {
            echo "--delete old stage start--";
            sh "docker-compose down"
            sh "docker ps -a"
            sh "docker container prune -f"
            sh "docker image prune --all --force"
        }
        stage("deploy stage") {
            echo "--deploy stage start--";
            sh "docker-compose up -d"
            echo "--deploy done--";
        }
    }
}