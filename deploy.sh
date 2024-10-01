#!/bin/sh
dir=$(dirname $0)
IP=$1
scp "$dir"/app/gospeldiver.sh root@$IP:
scp "$dir"/app/target/jib-image.tar root@$IP:app.tar

#start the server
ssh root@$IP 'sh gospeldiver.sh start ' $2