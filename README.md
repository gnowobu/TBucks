# TBucks: A management system for coffee shops

## Description
This project created a system for online coffee shops, enabling customers to order online
and the coffee shop owner to manage the business as well.

* Technical Overview:

This application is developed in Spring Framework by using Spring Boot, Hibernate, Spring RESTful web services, Postman, Maven, PostgresSql, Docker, Amazon SQS, and Amazon S3.

* Business Rules:

1. Object
    
    There are four objects: Coffee, Customer(User), Order and Role. 

1. Relationships
    
    1. One customer has multiple orders (One to Many).
    2. Each order can have multiple coffee (Many to Many).
    3. A customer(user) can have different roles (Many to Many).

* Development Approaches:

    1. Created java objects (Coffee, Customer, Order, Role).
    2. Used Hibernate to do object-relational mapping.
    3. Used JDBC to connect project with Postgres.
    4. Created repository, service and did test.
    5. Created filters to implement authentication and authorization
    6. Created Controllers and Restful APIs.
    7. Used mockito library to test AWS S3 Storage service.
    8. Integrated third-party application AWS SQS and tested by Mockito.


## Configure Local Environment
### setup local database
Reference to Postgres docker [image](https://hub.docker.com/_/postgres) for environment option.
```
docker run --name TBucks -e POSTGRES_USER=${username} -e POSTGRES_PASSWORD=${password} -e POSTGRES_DB=${databaseName} -p 5431:5432 -d postgres
```
### migrate database schema
Refer to flyway setup [documentation](https://flywaydb.org/documentation/), you can click the link to find the [migration schema](mvc/src/main/resources/db/migration)

<<<<<<< HEAD
    mvn clean complile flyway: migrate -Ddatabase.driver=org.postgresql.Driver -Ddatabase.dialect=org.hibernate.dialect.PostgreSQL9Dialect -Ddatabase.url=${DB_URL} -Ddatabase.user=${DB_USER} -Ddatabase.password=${DB_PASSWORD}
=======
    mvn clean compile flyway:migrate -Ddatabase.driver=org.postgresql.Driver -Ddatabase.dialect=org.hibernate.dialect.PostgreSQL9Dialect -Ddatabase.url=jdbc:postgresql:${DB_URL}:5432/${DB_NAME} -Ddatabase.user=${DB_USER} -Ddatabase.password=${DB_PASSWORD}
>>>>>>> c5da842b59b4fc585744792169796391c4b56fb5
    
    
## Test

- Pack up before unit test
    ```
    mvn clean compile install -DskipTests=true
    ```
- Start testing with following command. You might need to add Mockito and JUnit dependency.
    ```
    mvn test -Ddatabase.driver=org.postgresql.Driver -Ddatabase.dialect=org.hibernate.dialect.PostgreSQL9Dialect -Ddatabase.url=jdbc:postgresql:${DB_URL}:5432/${DB_NAME} -Ddatabase.user=${DB_USER} -Ddatabase.password=${DB_PASSWORD} -Dspring.profiles.active=unit -Dkey=test
    ```

## Packaging
```
mvn clean compile package -DskipTests=true
```

# CI/CD

You should have completed the following stages before you work with DevOps engineer.

  1. Upload source code to GitHub repository
  2. Fulfill unit test stage in docker container
  3. Package **.war** file in docker container
  4. Build Docker image with **.war** file and Dockerfile
  5. Launch containerized application successfully

## GitHub

Make sure the source code in the github is the latest(runnable) version.   

***IMPORTANT: DO NOT INCLUDE ANY CREDENTIAL IN THE CODE.***

## Unit Test
>Use `Docker` to pull `Maven` image and run an interactive container.
>
    docker pull maven:3.6.0-jdk-8
    docker run -it maven:3.6.0-jdk-8 /bin/bash

>Use `Git` to retrieve source code from `GitHub`.
>
    git clone <repository_url>
    
>Get into the project's folder, then use `Flyway` to migrate data.
>
    mvn clean compile flyway:migrate -Ddatabase.url=jdbc:postgresql://${database_url}:5432/${database_name} 
    -Ddatabase.user=${user_name} -Ddatabase.password=${password}
    
Notice: We are currently running in the container. Thus, the database connection is no longer localhost:5432.
You should inspect `postgreSQL` server container to find the IP address. Find the internal IP address of the container by using:
    
    docker inspect ${container_id} | grep "IPAddress"
    
>Run unit tests in the container.
>
    mvn test -Ddatabase.url=jdbc:postgresql://${database_url}:5432/${database_name} -Dspring.profiles.active=unit -Ddatabase.user=${user_name} 
    -Ddatabase.password=${password} -Daws.accessKeyId=${access_key} -Daws.secretKey=${secret_key} 
    -Ddatabase.dialect=org.hibernate.dialect.PostgreSQL9Dialect -Ddatabase.driver=org.postgresql.Driver