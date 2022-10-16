#!/bin/bash
cd /home/ec2-user/DNS_renewal
pwd
git pull origin main
docker-compose down
docker ps -a
docker container prune -f
docker image prune --all --force
docker-compose up -d
echo "deploy done"