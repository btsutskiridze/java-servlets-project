# java-servlets-project

### Description

This project is a simple Java Servlets project that uses a MySQL database to store and retrieve data. The project is a simple web application that allows users to create, read, update, and delete (CRUD) data from a database. The project uses the MVC design pattern and is built using Maven. The project uses the following technologies:

- Java
- Java Servlets
- MySQL
- Maven
- JUnit
- Mockito
- Tomcat
- Docker
- Docker Compose


# Setup

## Clone the repository

- Clone the repository: `git clone git@github.com:btsutskiridze/java-servlets-project.git`
- Navigate to the project directory: `cd java-servlets-project`

## Create the database

- Start MySQL Server
- Connect to MySQL 
- Create the database: `CREATE DATABASE servlets_demo;`
- Create the table: 
``` mysql
CREATE TABLE `servlets_demo`.`users` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `created_on` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email` (`email` ASC) VISIBLE,
  UNIQUE INDEX `id` (`id` ASC) VISIBLE,
  UNIQUE INDEX `username` (`username` ASC) VISIBLE);
```

## Configure the project

- Setup application.properties file
- Add the following properties to the application.properties file:
``` properties
db.url=jdbc:mysql://localhost:3306/db_name
db.username=sql_username
db.password=sql_password
```

### Run the project

- `docker-compose up -d`

### Test the project

- `mvn test`