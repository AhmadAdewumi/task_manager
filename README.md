# Task Manager API

A simple REST API built with Spring Boot and PostgreSQL to manage tasks. Users can create, read, update, delete tasks, and filter by status with pagination support.

## Features

- Create, retrieve, update, and delete tasks
- Filter tasks by status (`pending`, `in-progress`, `completed`)
- Pagination support (`page`, `limit`)
- Built with Spring Boot and PostgreSQL

## Tech Stack

- Java 17+
- Spring Boot
- PostgreSQL
- Spring Data JPA
- 
## Getting Started

### Prerequisites

- Java 17 or higher
- PostgreSQL
- Maven

### Database Setup

1. Create a PostgreSQL database called `task_manager`
2. Create a user and grant access, or use your existing PostgreSQL user

### Configure Application

Edit the `src/main/resources/application.properties` file:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/task_manager
spring.datasource.username=your_db_user
spring.datasource.password=your_db_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### Run the Application

```bash
mvn spring-boot:run
```

Once the application starts, it will be available at:

```
http://localhost:8080
```

## API Endpoints

### Create a Task

```
POST /tasks
```

Request Body:
```json
{
  "title": "Write README",
  "description": "Write a clean README file",
  "status": "pending"
}
```

### Get All Tasks

```
GET /tasks
```

Query Parameters:
- `status` (optional): Filter tasks by status
- `page` (optional): Page number for pagination
- `limit` (optional): Number of tasks per page

Example:
```
GET /tasks?status=completed&page=1&limit=10
```

### Get Task by ID

```
GET /tasks/{id}
```

### Update a Task

```
PUT /tasks/{id}
```

Request Body:
```json
{
  "title": "Update README",
  "description": "Improve the README clarity",
  "status": "in-progress"
}
```

### Delete a Task

```
DELETE /tasks/{id}
```

## Status Enum Values

The `status` field must be one of the following:

- `pending`
- `in-progress`
- `completed`
