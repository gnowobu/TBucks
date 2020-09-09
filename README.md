# TBucks: A management system for coffee shops

## Description
This project created a system for online coffee shops, enabling customers to order online
and the coffee shop owner to manage the business as well.

* Technical Overview:

This application is developed in Spring Framework by using Spring Boot, Hibernate, Spring RESTful web services, Postman, Maven, PostgresSql, Docker, Amazon SQS, and Amazon S3.

* Business Rules:

    * Object: Coffee, Customer/User, Order, Role 
        
    * Relationships:
        1. One customer can have many orders (One to Many)
        2. One customer can have multiple roles (Many to Many)
        3. One order can have multiple types of coffee (Many to Many)

    * Development Approaches
    1. Create java objects.
    1. Use Hibernate and JDBC to implement object-relational mapping with Postgres.
    1. Implement repository and service layer based on CRUD operation, test with JUnit.
    1. Begin to design REST api and implement by creating controllers.
    1. Implement authentication and authorization with JWT and filters.
    1. Integrate with AWS services, such as Amazon S3 (storage) and Amazon SQS (message queue), test with Mockito.
    1. Add other third-party apis such as Twilio to implement specific requirements.

## Configure Local Environment
### setup local database
Reference to Postgres docker [image](https://hub.docker.com/_/postgres) for environment option.
```
docker run --name TBucks -e POSTGRES_USER=${username} -e POSTGRES_PASSWORD=${password} -e POSTGRES_DB=${databaseName} -p 5431:5432 -d postgres
```
### migrate database schema
Refer to flyway setup [documentation](https://flywaydb.org/documentation/), you can click the link to find the [migration schema](mvc/src/main/resources/db/migration)

    mvn clean complile flyway: migrate -Ddatabse.url -Ddatabase.name -Ddatabase.user -Ddatabase.password

    

## Test

- Run test with following command. Make sure to add dependency for JUnit and Mockito
```
mvn test -Ddatabase.driver=org.postgresql.Driver -Ddatabase.dialect=org.hibernate.dialect.PostgreSQL9Dialect -Ddatabase.url=jdbc:postgresql://172.17.0.2:5432/TBucks -Ddatabase.user=test -Ddatabase.password=test -Dspring.profiles.active=unit -Dkey=test
```

## Packaging
```
mvn clean compile package -DskipTests=true
``` 



## CI/CD
You should have completed the following stages before you work with DevOps engineer.

  * Upload source code to GitHub repository
  * Fulfill unit test stage in docker container
        >Use `Docker` to pull `Maven` image and run an interactive container.
        >
            docker pull maven:3.6.0-jdk-8
        >
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

 
  * Package **.war** file in docker container
  * Build Docker image with **.war** file and Dockerfile
  * Launch containerized application successfully
