#!/usr/bin/env bash

cd credit-check-service
sh build-native.sh -Dquarkus.profile=dcn
cd ..

docker compose -f docker-compose.yaml up -d