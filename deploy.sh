#!/bin/bash
docker-compose down
docker ps -a
docker container prune -f
docker image prune --all --force
docker-compose up -d
echo "deploy done"