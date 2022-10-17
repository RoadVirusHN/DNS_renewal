pipeline {
    agent dns

    stage {
        stage("pull stage") {
            echo "--pull stage start--"
            cd ~/DNS_renewal/
            git pull origin main
        }
        stage("delete old stage") {
            echo "--delete old stage start--"
            docker-compose down 
            docker ps -a
            docker container prune -f
            docker image prune --all --force
        }
        stage("deploy stage") {
            echo "--deploy stage start--"
            docker-compose up -d
            echo "--deploy done--"
        }
    }
}