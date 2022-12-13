docker pull roadvirushn/sns_backend
docker pull roadvirushn/sns_frontend
docker network create -d bridge sns_network
docker run -p 8000:8080 --env-file .env -d --cpus=0.3 --memory=256m --restart unless-stopped --network=sns_network --name sns_backend roadvirushn/sns_backend:latest
docker run -p 80:80 -e "VUE_APP_BACKEND=0.0.0.0" -d --cpus=0.3 --memory=128m --restart unless-stopped --network=sns_network --name sns_frontend roadvirushn/sns_frontend:latest
