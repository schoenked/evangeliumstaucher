#!/bin/sh
# -*- coding: utf-8 -*-

APP_JAR=app.jar
if [ "$2" = "public" ]; then
    PROFILES=default,db,prodDB,creds,logging
else
    PROFILES=default,db,prodDB,creds,accounts,logging
fi

start_app() {
  stop_app
  echo deleting images
  docker image prune -af
  echo loading image
  docker load -i app.tar
  docker run --name app \
  -e SPRING_PROFILES_ACTIVE=$PROFILES \
  -v /etc/letsencrypt/live/evangeliumstaucher.nobler.tech/myp12file.p12:/home/ubuntu/myp12file.p12 \
  -v /root/.postgresql/root.crt:/root/.postgresql/root.crt \
  -v /root/.sword:/root/.sword \
  -v /root/.jsword:/root/.jsword \
  -v /root/logs:/logs \
  -p 443:443 \
  --restart always \
  $(docker images --format "{{.Repository}}") &
  background_pid=$!
  echo PID: $background_pid
}

stop_app() {
  echo killing running containers
  docker kill $(docker ps -q)
  docker rm $(docker ps -qa)
}

if [ "$1" = "start" ]; then
  start_app
elif [ "$1" = "stop" ]; then
  stop_app
else
  echo "Usage: $0 start|stop"
fi
