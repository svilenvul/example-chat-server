# Dummy Chat Server

## Description

This is an implementation of a Dummy Chat Server which should serve as a sample project
for a backend implementation following the principles of OOP and
Clean Architecture.

The server is implemented with Spring Boot and H2 in-memory database.

## Project Structure

The project has the following package structure:

```text
src/main/java
└── com
    └── example
        └── chatserver
            ├── adapter
            │   ├── entities
            │   ├── repository
            │   └── validation
            ├── controller
            ├── domain
            │   ├── exception
            │   ├── mapping
            │   ├── models
            │   ├── util
            │   └── validation
            └── service
```

## APIs

The server exposes one API (POST `messages/{type}`), which supports two type of
messages (`send_text` and `send_emotion`).

Postman collection with sample requests is provided here:

- [Postman collection](docs/Chat%20Server.postman_collection.json)

## Run Unit Tests

Unit tests can be started with the following command:

```properties
./gradlew test
```

## Start Server

In order to start the server execute:

```properties
./gradlew bootRun
```

The server will be listening on port `8080`.
