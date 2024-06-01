
#!/bin/bash

dir=$(dirname $0)
IP=$1

scp "$dir"/app/gospeldiver.sh root@$IP:
scp "$dir"/app/target/app*.jar root@$IP:app.jar

#start the server
ssh root@$IP 'sh gospeldiver.sh start ' $2