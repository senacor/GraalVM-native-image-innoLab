#!/usr/bin/env bash

pushd credit-check-service || exit 1
./build.sh
popd || exit 1

docker-compose -f docker-compose-jvm.yaml up --detach
