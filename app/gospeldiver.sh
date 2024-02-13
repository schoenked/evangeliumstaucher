#!/bin/bash

APP_JAR=app.jar

if [ "$1" = "start" ]; then
  java -jar "$APP_JAR"
elif [ "$1" = "stop" ]; then
  echo "no support"
  #java -cp "$APP_JAR:<other jars>" $APP_MAIN stop
else
  echo "Usage: $0 start|stop"
fi