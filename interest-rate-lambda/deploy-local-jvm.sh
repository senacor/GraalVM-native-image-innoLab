#!/bin/bash

mvn clean verify

sam local start-api --template src/main/sam/jvm/template.yaml