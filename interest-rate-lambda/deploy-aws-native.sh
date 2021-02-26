#!/bin/bash

mvn clean install -Pnative -Dnative-image.docker-build=true

pushd src/main/sam/native || exit 1
sam deploy
popd || exit 1