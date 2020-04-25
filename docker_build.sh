#!/usr/bin/env bash

./gradlew jar
docker build . -t experimentalsoftware/noauth:latest
