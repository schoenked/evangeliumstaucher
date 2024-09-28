#!/bin/bash
# -*- coding: utf-8 -*-

APP_JAR=app.jar
if [ "$2" = "public" ]; then
    PROFILES=default,db,creds,logging
else
    PROFILES=default,db,creds,accounts,logging
fi

start_app() {
  stop_app
  echo deleting images
  docker image prune -af
  echo loading image
  docker load -i app.tar
  docker run --name app \
  -e SPRING_PROFILES_ACTIVE=x \

  $(docker images --format "{{.Repository}}") &
  background_pid=$!
  echo PID: $background_pid
}

stop_app() {
  echo killing running containers
  docker container prune -f
}

if [ "$1" = "start" ]; then
  start_app
elif [ "$1" = "stop" ]; then
  stop_app
else
  echo "Usage: $0 start|stop"
fi
