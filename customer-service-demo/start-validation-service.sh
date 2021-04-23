#!/bin/bash

set -e

location=src/main/validation
service=validation-service
host_port=9082

echo "Build docker image.."
docker build --tag ${service} ${location}

echo "Start ${service}.."
docker run \
  --detach \
  --publish ${host_port}:8082 \
  --name ${service} \
  ${service}

echo "${service} started successfully on port ${host_port}"