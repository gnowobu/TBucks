# TBucks: A management system for coffee shops

## Description
This project created a system for online coffee shops, enabling customers to order online
and the coffee shop owner to manage the business as well.

* Technical Overview:

This application is developed in Spring Framework by using Spring Boot, Hibernate, Spring RESTful web services, Postman, Maven, PostgresSql, Docker, Amazon SQS, and Amazon S3.

* Business Rules:

1. Object

1. Relationships

1. Development Approaches


## Configure Local Environment
### setup local database
Reference to Postgres docker [image](https://hub.docker.com/_/postgres) for environment option.
```
docker run --name TBucks -e POSTGRES_USER=${username} -e POSTGRES_PASSWORD=${password} -e POSTGRES_DB=${databaseName} -p 5431:5432 -d postgres
```
### migrate database schema
Refer to flyway setup [documentation](https://flywaydb.org/documentation/), you can click the link to find the [migration schema](mvc/src/main/resources/db/migration)

    mvn clean compile flyway:migrate -Ddatabase.driver=org.postgresql.Driver -Ddatabase.dialect=org.hibernate.dialect.PostgreSQL9Dialect -Ddatabase.url=jdbc:postgresql:${DB_URL}:5432/${DB_NAME} -Ddatabase.user=${DB_USER} -Ddatabase.password=${DB_PASSWORD}
    
    
### test command line
    mvn test -Ddatabase.driver=org.postgresql.Driver -Ddatabase.dialect=org.hibernate.dialect.PostgreSQL9Dialect -Ddatabase.url=jdbc:postgresql://172.17.0.2:5432/TBucks -Ddatabase.user=test -Ddatabase.password=test -Dspring.profiles.active=unit -Dkey=test
