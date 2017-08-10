Spring Project for department and employee management.
==========================================
## Introduction ##
This application is used with Spring Boot,REST API and Security. 

## Requirements ##
* Maven 3.0+
* MySQL 5.6 or later
* JDK 1.8 or later
* Spring Boot 1.3

## Modules ##
* `model`: Implementation of entity object response to DB (1 to manay)
* `restapi`: RESTful Web Services
* `security`: REST endpoints and web interface with Basic Authentication

## Installation ##
mvn package
mvn install

## Run Application ##
cd DeptEmplManagement

mvn spring-boot:run

