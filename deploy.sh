#!/bin/bash

IP=$1

scp app/gospeldiver.sh root@$IP:
scp app/target/app-*.jar root@$IP:app.jar
