# evangeliumsTaucher

## Demo
The game is provided at:

https://evangeliumstaucher.nobler.tech

## Description

This is the project for the evangeliumsTaucher game.
It's a web application that uses

| Framework/Project | Link |
|-------------------|------|
| Spring Boot       |   https://spring.io/projects/spring-boot |
| Thymeleaf         |   https://www.thymeleaf.org |
| Bootstrap         |   https://getbootstrap.com  |
| Hibernate         |   https://hibernate.org   |
| Sword             |   https://www.crosswire.org/sword |


## Getting started

#### System requirements

##### Java

For installation [https://adoptium.net/de/temurin/releases/]()

##### Database

By using Hibernate ORM, the project is high flexible at this point.
The empty database can be set up by the property on startup
```
spring.jpa.hibernate.ddl-auto: create
```

## Starting the application

To use the Maven wrapper run the commands with `./mvnw` (Unix) or `.\mvnw` (Windows) instead or `mvn`

#### Build
```mvn
mvn clean install
```
- loads the libraries
- generates custom themed bootstrap
- compiles and tests the application

#### Run
```
mvn -pl app spring-boot:run 
```
### Install SSL certificate
#### Using Lets encrypt
```
apt install certbot
```
Replace evangeliumstaucher.nobler.tech with your hostname
```
certbot certonly --standalone -d evangeliumstaucher.nobler.tech
```
#### Convert cert to pks
```
crontab -e
```
insert
```
0 2 * * * cd /etc/letsencrypt/live/evangeliumstaucher.nobler.tech && sh convertCert.sh >> cron.log
```
### Install docker
see instruction
https://docs.docker.com/engine/install/ubuntu/


---
## God bless!
