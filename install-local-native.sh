#!/usr/bin/env bash

cd credit-check-service
sh build-native.sh
cd ..
## required to create the local .m2 folder used by the docker image
cd validation-service
sh build.sh
cd ..
## required to create the local .m2 folder used by the docker image
cd customer-service
sh build.sh
cd ..

docker compose -f docker-compose.yaml up -d