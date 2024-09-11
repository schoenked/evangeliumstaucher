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
  java -jar "$APP_JAR" -Xmx473m --spring.profiles.active=$PROFILES &
  background_pid=$!
  echo $background_pid > gospeldiver.pid
  echo -17 > /proc/$background_pid/oom_adj
}

stop_app() {
  if [ -f gospeldiver.pid ]; then
    pid=$(cat gospeldiver.pid)

    if ps -p $pid > /dev/null; then
      kill -15 $pid
      rm gospeldiver.pid
    else
      echo "PID file exists but the process is not running."
    fi
  else
    echo "PID file does not exist. The application may not be running."
  fi
}

if [ "$1" = "start" ]; then
  start_app
elif [ "$1" = "stop" ]; then
  stop_app
else
  echo "Usage: $0 start|stop"
fi
