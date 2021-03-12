#!/bin/bash

mvn clean verify

pushd src/main/sam/jvm || exit 1
sam deploy
popd || exit 1