# evangeliumsTaucher

This is the project for the evangeliumsTaucher game.
It's a web application that uses

- Spring Boot
- Thymeleaf
- Bootstrap
- Hibernate (coming)
- API.Bible

Get started

---

## Getting started

#### System requirements

- Java

For installation [https://adoptium.net/de/temurin/releases/]()

#### Get a Bible.API key

You have to get your API key for connecting to the API.Bible

see [https://docs.api.bible/getting-started/make-your-first-api-call]()

copy the key to src/main/resources/application-creds.properties

## Starting the application

To use the Maven wrapper run the commands with `./mvnw` (Unix) or `.\mvnw` (Windows) instead ov `mvn`

#### Build
```mvn
mvn clean install -pl app spring-boot:run 
```
- loads the libraries
- generates API code
- generates custom themed bootstrap
- compiles and tests the application

#### Run
```
mvn -pl app spring-boot:run 
```

## TODOs

* [ ]  responsive scaling
* [ ]  selector styling
* [ ]  copyright
* [ ]  footer/impress
* [ ]  database
* [ ]  highscoring

---

Have fun. God bless!
