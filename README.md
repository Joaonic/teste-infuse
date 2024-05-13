# Infuse Assessment Documentation

## Introduction

This project is a Spring Boot application aimed at managing orders. It allows the creation and consultation of orders
through a RESTful API. The project uses clean architecture practices, separating responsibilities into clearly defined
layers and facilitating application maintenance and scalability.

## Architecture

The project uses Spring Boot and follows a layer-based architecture, facilitating the separation of responsibilities and
code maintenance. The architecture can be divided into the following main layers::

- **Presentation Layer (api)**: Contains the REST
  controllers [OrderRestController](src/main/java/com/testes/infuse/orders/api/rest/OrderRestController.java) that
  handle HTTP requests, using
  mappers [OrderRequestMapper](src/main/java/com/testes/infuse/orders/api/shared/mapper/OrderRequestMapper.java) to
  convert input data into formats usable in domain services.

- **Domain Layer (core)**: Defines the business rules and application logic. Includes use case
  interfaces [OrderRequestUseCase](src/main/java/com/testes/infuse/orders/core/port/in/OrderRequestUseCase.java),
  service
  implementations [OrderService](src/main/java/com/testes/infuse/orders/core/domain/service/impl/OrderServiceImpl.java),
  and data transfer
  mappers [OrderDtoMapper](src/main/java/com/testes/infuse/orders/core/application/mapper/OrderDtoMapper.java).

- **Persistence Layer (infrastructure)**: Responsible for implementing the
  repositories [OrderRepositoryAdapter](src/main/java/com/testes/infuse/orders/infrastructure/persistence/mysql/OrderRepositoryAdapter.java),
  using JPA to interact with the MySQL database. Query specifications are defined
  in [OrderSpecification](src/main/java/com/testes/infuse/orders/infrastructure/persistence/mysql/jpa/specification/OrderSpecification.java).

## Technologies Used

- **Spring Boot**: Framework to facilitate the configuration and development of new Spring applications.
- **MySQL**: Database management system for data persistence.
- **Swagger**: Used for API documentation.

Here is the full section on how to use the application, including instructions for running the project locally with
Maven and using Docker Compose. This guide assumes that all necessary files, such as the Dockerfile and
docker-compose.yml, are already configured in the project.

## How to use

This guide covers how to run the Orders application using Maven and Docker Compose.

### Running with Maven

To run the application locally with Maven, follow these steps:

1. **Environment Configuration**:
    - Java JDK 17 or higher: [Download JDK](https://www.oracle.com/java/technologies/downloads/#java17)
    - Maven 3.8 or higher: [Download Maven](https://maven.apache.org/download.cgi)
    - MySQL Server 8.1 or higher: [Download MySQL](https://dev.mysql.com/downloads/mysql/)
    - If you have Docker on the machine, it is possible to run the application with just Docker, as shown in the next
      steps: [Download Docker](https://docs.docker.com/engine/install/)

2. **Clone the Project**:
   Make a clone of the Git repository where the project is stored.
    ```bash
    git clone https://github.com/Joaonic/teste-infuse.git
    test-infuse cd
    ```

3. **Configure the Database**:
   Make sure MySQL is configured as per the instructions in the Environment Configuration section. Add environment
   variables `DB_USER` and `DB_PASS` accordingly to your database `username` and `password` if you are not using mysql defaults.

4. **Execute the Project**:
   Use the following command to start the application:
    ```bash
    mvn spring-boot:run
    ```
   This will start the server on the default port 8080 (or the one you configured in `application.yaml`). The
   application will create a database named `infuse` and the orders table automatically at startup. The SQL scripts used
   are in the [DB](src/main/resources/db) directory. 50 random orders will also be inserted to populate the database
   with test data.

### Running with Docker Compose

If you prefer to use Docker Compose to run the application, follow these steps:

1. **Image Build**:
   First, build the project's Docker image if you haven't already built it or downloaded it from a registry.
    ```bash
    docker compose build
    ```

2. **Run with Docker Compose**:
   With the image built, use Docker Compose to start the application along with the necessary dependencies (such as the
   MySQL database):
    ```bash
    docker compose up
    ```
   This will start all services defined in `docker-compose.yml`, including the application and MySQL service.

3. **Verification**:
   Access `http://localhost:8080` to check if the application is running correctly. Use
   the [Swagger](src/main/resources/static/swagger.json) documentation to explore the API endpoints.

When using Docker Compose, ensure that all volumes and ports are configured correctly in the `docker-compose.yml` file
to avoid port conflicts and ensure data persistence.

## Obs

- Customer code and order Control Number are treated as string because despite being a numeric identification, it was considered as a String due to the possibility of being sent with 0 on the left.
- Dockerfile was made with multi-stage and layered construction to make the image lighter and the build faster.
