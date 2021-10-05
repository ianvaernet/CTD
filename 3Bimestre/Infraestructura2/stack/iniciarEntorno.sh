#!/bin/bash

(sudo rm -rf nexus_data jenkins_home) 2>&1 > /dev/null

mkdir -p nexus_data jenkins_home 2>&1 > /dev/null
chmod 777 nexus_data
chmod 777 jenkins_home

docker-compose rm -f
docker-compose pull
docker-compose build --no-cache
docker-compose up --force-recreate
