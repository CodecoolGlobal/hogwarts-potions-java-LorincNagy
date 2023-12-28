# Employee Data Management - Spring Boot Project

## Overview
This project is developed as a web application for efficiently managing employee data. It leverages various technologies, including Spring Boot, Spring Data JPA, and PostgreSQL for database management.

## Server side

### Environment Setup
Clone this repository to your local machine.

### Database Configuration
Create a PostgreSQL database and set the following environment variables with your database connection details:

SPRING_DATASOURCE_URL: The URL of your PostgreSQL database.
SPRING_DATASOURCE_USERNAME: Your database username.
SPRING_DATASOURCE_PASSWORD: Your database password.

### Building and Running the Application
Use the following Maven command to build and run the Spring Boot application:

mvn spring-boot:run
The application will start on port 8080 by default.

### Technology Stack
Spring Boot
Spring Data JPA
PostgreSQL
API Endpoints
You can test the REST API endpoints using tools like Postman or curl. Below are some sample endpoints:

GET all room: http://localhost:8080/room
GET room by ID: http://localhost:8080/room/{id}
POST new room: http://localhost:8080/room
PUT update room: http://localhost:8080/room/{id}
DELETE room by ID: http://localhost:8080/room/{id}
Feel free to explore and customize this Spring Boot project for your employee data management needs.
