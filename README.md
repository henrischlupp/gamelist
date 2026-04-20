
# GameList

GameList is a simple Spring Boot and Thymeleaf web application for managing a personal video game collection.

## Features

- Public landing page that shows recently added games without requiring login
- Private library for authenticated users
- CRUD operations for game entries
- Status tracking with `PLAYABLE`, `UNFINISHED`, and `COMPLETED`
- Bean Validation for registration and game forms
- Spring Security form login
- Password reset by email
- REST endpoint at `/api/games`
- PostgreSQL-ready configuration with environment variables
- Local development support with H2 in-memory database

## Tech stack

- Java 21
- Spring Boot
- Spring MVC
- Thymeleaf
- Spring Data JPA
- Spring Security
- Bean Validation
- PostgreSQL
- H2 for local development

## Data model

The application uses at least two related database tables:

- `app_users` stores registered users
- `games` stores game entries and links each game to its owner

This gives the project a simple one-to-many relationship for the course requirements.

## Running locally

1. Install Java 21 and Maven.
2. Run the app:

```bash
mvn spring-boot:run
```

3. Open `http://localhost:8080`

Demo login:

- Username: `demo`
- Password: `password`

## PostgreSQL and cloud deployment

For cloud deployment later, configure these environment variables:

- `JDBC_DATABASE_URL`
- `JDBC_DATABASE_USERNAME`
- `JDBC_DATABASE_PASSWORD`
- `JDBC_DATABASE_DRIVER=org.postgresql.Driver`
- `MAIL_USERNAME`
- `MAIL_APP_PASSWORD`
- `APP_BASE_URL`

The app defaults to H2 locally, but it can switch to PostgreSQL automatically when those values are provided.
