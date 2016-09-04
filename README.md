# AirTicketReservationSystem

Air Ticket Reservation System

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisities

Maven or Graddle
Mysql
Java 1.8

### Installing

Gradle

gradle clean build
gradle test
gradle clean build install --debug
gradle clean build install
gradle clean build install bootRun

Maven

mvn clean
mvn compile package
mvn install
-- Run maven like this to generate report and api
mvn -Dprofile2 test site:site site:deploy --debug
mvn -Dprofile2 test site:site site:deploy
mvn -Dprofile2 test site:site site:deploy spring-boot:run

## Endpoints develop

Spring Boot

POST
Content-Type: application/json
http://localhost:8080/airticket/auth
{"username": "admin","password": "pass"}


Spring MVC

GET
Content-Type: text/html
http://localhost:8080/login.do

# Maven Report

http://localhost:8080/maven-report/index.html
