# Estate

This project is using Maven, Java 17 and MySQL database.

## Repository Github

https://github.com/Florilege37/chatop

### MySQL

Download MySQL : https://dev.mysql.com/downloads/installer/
Open MySQL Command Line Client and execute these commands : 
> mysql -u root -p chatop < file/path/script.sql
> use chatop; 

SQL script for creating the database is available here `ressources/sql/script.sql`

### application.properties

Change your server port `server.port=` and `chatop.openapi.dev-url=localhost:`

Change your database URL `spring.datasource.url=`

Change your database username `spring.datasource.username=`
and password `spring.datasource.password=`

## Start the project

Git clone:

> git clone https://github.com/Florilege37/chatop.git
> cd chatop  
> mvn clean install
> mvn spring-boot:run

## Swagger 

localhost:9000/chatop-documentation