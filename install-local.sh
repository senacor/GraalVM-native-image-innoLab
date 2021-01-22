#!/usr/bin/env bash

cd credit-check-service
sh build.sh -Dquarkus.profile=dcjvm
cd ../customer-service
sh build.sh
cd ..

docker compose -f docker-compose-jvm.yaml up -d
