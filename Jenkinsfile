pipeline {
    agent {label "dns-node"}
        
    stages {
        stage("pull stage") {
            steps{                        
                dir("/home/ec2-user/DNS_renewal/") {
                    echo "--pull stage start--"
                    sh "pwd"
                    sh "git pull origin main"
                }
            }
        }
        stage("delete old stage") {
            steps{                        
                dir("/home/ec2-user/DNS_renewal/") {                
                    echo "--delete old stage start--"
                    
                    sh "docker-compose down"
                    sh "docker ps -a"
                    sh "docker container prune -f"
                    sh "docker image prune --all --force"
                }
            }
        }
        stage("deploy stage") {
            steps {                                        
                dir("/home/ec2-user/DNS_renewal/") {
                    echo "--deploy stage start--"
                    sh "docker-compose up -d"
                    echo "--deploy done--"
                }
            }
        }
    }    
}