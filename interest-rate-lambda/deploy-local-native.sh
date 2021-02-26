#!/bin/bash

mvn clean install -Pnative -Dnative-image.docker-build=true

sam local start-api --template src/main/sam/native/template.yaml