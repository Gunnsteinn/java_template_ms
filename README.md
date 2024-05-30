# Template Spring Boot Application

This is a template project for a Spring Boot application with JWT authentication, MySQL database integration, and user management.

## Technologies Used

- **Java**: 17
- **Spring Boot**: 3.2.5
    - Spring Boot Starter Web
    - Spring Boot Starter Security
    - Spring Boot Starter Validation
    - Spring Boot Starter Actuator
    - Spring Boot Starter Data JPA
- **MySQL**: 8.0 (Docker)
- **JWT**: JJWT 0.12.5
- **Lombok**: 1.18.32
- **ModelMapper**: 3.2.0
- **Log4j2**: for logging
- **SpringDoc OpenAPI**: 2.3.0
- **Mockito**: 5.12.0
- **Easy Random**: 5.0.0
- **Docker Compose**: for containerized MySQL

## Prerequisites

- **Java Development Kit (JDK)**: 17
- **Maven**: 3.6+
- **Docker**: 20.10+
- **Docker Compose**: 1.29+

## Setup and Running the Application

### Step 1: Clone the repository

```sh
git clone https://github.com/your-repo/template-app.git
cd template-app
```

### Step 2: Build the project
```sh
mvn clean install
```

### Step 3: Set up the MySQL database with Docker
Start the MySQL container:
```sh
docker-compose up -d --build
```

### Step 4: Running the Application:

**Using Java:**
```sh
java -jar target/app-0.0.1-SNAPSHOT.jar
```

## API Documentation
The API documentation is available at http://localhost:8089/swagger-ui.html.

## User Controller Endpoints
- GET /api/v1/users: Get a list of active users. Requires USER role.
- GET /api/v1/users/{id}: Get a user by its ID. Requires USER role.
- GET /api/v1/users/references/{reference}: Get a user by its reference. Requires USER role.
- POST /api/v1/users: Create a new user. Requires ADMIN role.
- POST /api/v1/users/register: Register a new user.
- PUT /api/v1/users/{id}: Update an existing user. Requires ADMIN role.
- DELETE /api/v1/users/{id}: Delete a user. Requires ADMIN role.

## Security
This application uses JWT for authentication. The JwtValidationFilter class handles the validation of JWT tokens.
