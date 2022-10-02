# Kata Bank Account 02/10/2022

This project was created with Spring Boot and use openjdk 1.8

## Getting Started

To run this project, please follow those instructions

### Prerequises

Clone this repository:

```text
    git clone git@github.com:YanisRIDA/KBA_SG.git
```

Then open the project with the IDE of your choice, Right click on the main class named Application, and then press "run Application".

You can also execute the project with maven:
```text
    mvn package && java -jar target/kataBankAccount-0.0.1-SNAPSHOT.jar
```

To execute the unit tests alone:
```text
    mvn test
```

## Documentation

When the server is running you can access the API Swagger at this URL:

```text
    http://localhost:8080/swagger-ui.html
```

## API

With this API, you can create bank accounts and register deposit and withdrawal operations.

When the API is running, an embedded Apache Tomcat Server will be running at:

```text
    http://localhost:8080/
```  

First of all, you need to create a bank account with a first name and a last name

With CURL:
```CURL
    curl -X POST \
    "http://localhost:8080/api/v1/bankaccounts"\
    -H "accept: */*" \
    -H "Content-Type: application/json" \
    -d "{ \"firstName\": \"Yanis\", \"lastName\": \"RIDA\"}"
```  

Then you are able to make POST or GET Requests in order to make deposit and withdrawal operations 
or to consult your bank account

```text
    http://localhost:8080/bankaccounts/{id}/depose
    http://localhost:8080/bankaccounts/{id}/withdraw
    http://localhost:8080/bankaccounts/{id}
```  

For a deposit, with CURL:

```CURL
    curl -X POST \
    "http://localhost:8080/api/v1/bankaccounts/0/depose?amount=100" \
    -H "accept: */*"
```  

For a withdrawal, with CURL:

```CURL
    curl -X POST \
    "http://localhost:8080/api/v1/bankaccounts/0/withdraw?amount=100" \
    -H "accept: */*"
```  

You can then make GET Requests in order to consult your balance and retrieve your operation history:

```text
    http://localhost:8080/bankaccounts/{id}
```  

With CURL:

```CURL
    curl -X GET \
    "http://localhost:8080/api/v1/bankaccounts/0" \
    -H "accept: */*"
```  


