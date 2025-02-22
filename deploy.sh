#!/bin/sh
# -*- coding: utf-8 -*-

dir=$(dirname $0)
IP=$1
scp "$dir"/app/gospeldiver.sh root@$IP:
rsync "$dir"/app/target/jib-image.tar root@$IP:app.tar

#start the server
ssh root@$IP 'sh gospeldiver.sh start ' $2