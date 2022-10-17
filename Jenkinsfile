pipeline {
    agent dns

    stage {
        stage("pull stage") {
            cd ~/DNS_renewal/
            git pull origin main
        }
        stage("delete old stage") {
            docker-compose down 
            docker ps -a
            docker container prune -f
            docker image prune --all --force
        }
        stage("deploy stage") {
            docker-compose up -d
        }
    }
}