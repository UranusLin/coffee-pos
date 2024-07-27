# Coffee POS System

This project is a Coffee POS (Point of Sale) system built with Spring Boot 3.4.0 and Java 17. It includes features such as accounting, checkout, and inventory management. The project is initialized using Spring Initializr with the following dependencies:

- Spring DevTools
- Lombok
- Spring Web
- Spring Security
- Spring Data JPA
- Flyway
- MySQL

## Project Structure

```
pos/
├── src/
│ ├── main/
│ │ ├── java/
│ │ │ └── com/
│ │ │ └── coffee/
│ │ │ └── pos/
│ │ │ ├── controller/
│ │ │ │ └── HealthCheckController.java
│ │ │ ├── model/
│ │ │ │ └── ...
│ │ │ ├── repository/
│ │ │ │ └── ...
│ │ │ ├── service/
│ │ │ │ └── ...
│ │ │ └── PosApplication.java
│ │ ├── resources/
│ │ │ ├── db/
│ │ │ │ └── migration/
│ │ │ │ └── V1__Init.sql
│ │ │ ├── application.properties
│ │ │ └── ...
│ ├── test/
│ │ ├── java/
│ │ │ └── com/
│ │ │ └── coffee/
│ │ │ └── pos/
│ │ │ └── ...
│ │ └── resources/
│ │ └── ...
├── mvnw
├── mvnw.cmd
├── pom.xml
├── Makefile
└── README.md
```


## Getting Started

### Prerequisites

- Java 17
- Maven
- Docker

### Setting Up MySQL with Docker

Before running the application, you need to set up a MySQL database using Docker. Use the following command to start a MySQL container:

```bash
docker run --name mysql-container -e MYSQL_ROOT_PASSWORD=my-secret-pw -p 3306:3306 -d mysql:latest
```

### Application Configuration
Configure the database connection in `src/main/resources/application.properties` or `src/main/resources/application.yml`:

`src/main/resources/application.properties`
```
spring.datasource.url=jdbc:mysql://localhost:3306/pos
spring.datasource.username=root
spring.datasource.password=my-secret-pw
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

spring.flyway.baselineOnMigrate=true
```
`src/main/resources/application.yml`

```yml
spring:
  application:
    name: Coffee_pos
  jpa:
    show-sql: true
  datasource:
    url: jdbc:mysql://localhost:3306/Coffee_pos
    username: root
    password: my-secret-pw
    driver-class-name: com.mysql.cj.jdbc.Driver
  flyway:
    baselineOnMigrate: true
```

### Building and Running the Application
To build and run the application, use the following Makefile commands:

1. Clean and package the application:
```make
make clean
```

2. Run the application:
```make
make dev
```

Deployed commend
```make
make all
```
or
1. Clean and package the application:
```make
make clean
```

2. build application:
```make
make build
```

3. run application:
```make
make run
```

### API Endpoints
#### Health Check
Check if the application is running by accessing the following endpoint:
```
GET /health
```
Response:
```json
{
  "status": "Application is running"
}
```

### Additional Information
This project uses the following key dependencies:

* **Spring Boot DevTool**s**: Provides hot-reloading and other development features.
* **Lombok**: Reduces boilerplate code by generating getters, setters, and constructors.
* **Spring Web**: Enables building RESTful web services.
* **Spring Security**: Provides security features such as authentication and authorization.
* **Spring Data JPA**: Simplifies data access using JPA (Java Persistence API).
* **Flyway**: Manages database migrations.
* **MySQL**: Used as the database for this application.

### Contributing
If you would like to contribute to this project, please fork the repository and submit a pull request. For major changes, please open an issue first to discuss what you would like to change.