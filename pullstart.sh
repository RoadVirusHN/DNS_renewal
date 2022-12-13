sudo amazon-linux-extra install docker -y
sudo systemctl enable docker
sudo systemctl start docker
sudo docker pull roadvirushn/sns_db
sudo docker pull roadvirushn/sns_backend
sudo docker pull roadvirushn/sns_frontend
sudo docker network create -d bridge sns_network
sudo docker run -p 3306:3306 --env-file .env -d --cpus=0.3 --memory=512m --restart unless-stopped --network=sns_network --name sns_db roadvirushn/sns_db:latest
sudo docker run -p 8000:8080 --env-file .env -d --cpus=0.3 --memory=256m --restart unless-stopped --network=sns_network --name sns_backend roadvirushn/sns_backend:latest
sudo docker run -p 80:80 -env "BACKEND_ADDR=127.0.0.1" -d --cpus=0.3 --memory=128m --restart unless-stopped --network=sns_network --name sns_frontend roadvirushn/sns_frontend:latest
