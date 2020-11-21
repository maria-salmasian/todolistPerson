# toDoListPersonApplication

ToDoListPerson is a Spring Boot application that is used to add/update/get/ delete users and their to-do lists.                     
Each person has his/her to-do list. Each to-do list has a status.                     
Features, instructions and details are listed below.                     

- endpoint: http://localhost:8080/

# Prerequisites
JDK 1.8                     
Spring Boot                     
Maven 3.6.3                     
PostgreSql                     

# Data
Data is stored in PostgreSql database                     
Tables:                     
    users                     
    to_do_list
    gender                     
    profession                     
    status                     
*last three are enum tables*                     

![diagram](https://user-images.githubusercontent.com/67556986/99569478-1d901500-29ea-11eb-98fc-cbc6adf0dff1.png)


# Libraries used
Hibernate Validation API                     
Swagger API: http://localhost:8080/swagger-ui.html#/                     
Lombok                     
JSON Library                     


# External tools
Postman                     

# Features
- User and To-do List CRUD operations
- Logger
- Custom exceptions
- Spring security
- Unit tests

# Package structure

ws: sub packages : controller , dto , exception, converter)                      
core: sub packages : model, service, serviceImpl                     
infrastructure: subpackages : entity, repositories                     
utils: enumeration                     
visibility:                     
ws -> core -> infrastructure                     
object  conversation: model mapper                     


# Instructions: getting started
Content-Type: application/json;charset=UTF-8 Accept: application/json;charset=UTF-8                     
Headers:                     
To get user: GET: http://localhost:8080/user/{id}                     
To get all users: GET: http://localhost:8080/user                     
To create user: POST: http://localhost:8080/user                     
e.g. {                     
         "name": "name",                     
         "surname": "surname",                     
         "salary": 0,                     
         "email": "email",                     
         "age": 0,                     
         "genderId": 1,                     
         "professionId": 2,                     
         "passportNo": 0                     
     }                     
     
To update user: PUT: http://localhost:8080/user/{id}                     
To delete user: DELETE: http://localhost:8080/user/{id}                     


To get toDoList: GET: http://localhost:8080/toDoList/{id}                     
To get all toDoLists: GET: http://localhost:8080/toDoLists (request params available: boolean active, boolean ordered, integer ststus)                     
e.g. http://localhost:8080/toDoList?ordered=true&active=true                     

To create toDoList: POST: http://localhost:8080/toDoList                     
e.g.  {"userId": 1,                     
         "statusId": 1,                     
         "description": "do something"}                     
         
To update toDoList: PUT: http://localhost:8080/toDoList/{id}                     
To delete toDoList: DELETE: http://localhost:8080/toDoList/{id}                     
To delete all toDoLists: DELETE: http://localhost:8080/toDoList                     
