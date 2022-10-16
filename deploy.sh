#!/bin/bash
echo "Stop old version"
docker-compose down
echo "build new image"
docker ps -a
docker container prune -f
docker image prune --all --force
echo "deploy new version"
docker-compose up -d
echo "deploy done"