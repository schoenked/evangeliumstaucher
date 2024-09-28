# evangeliumsTaucher

This is the project for the evangeliumsTaucher game.
It's a web application that uses

- Spring Boot
- Thymeleaf
- Bootstrap
- Hibernate (coming)
- Sword

Get started

---

## Getting started

#### System requirements

- Java

For installation [https://adoptium.net/de/temurin/releases/]()

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


## TODOs

* [ ]  copyright

---

Have fun. 
## God bless!
