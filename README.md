# GameList

GameList is a Spring Boot and Thymeleaf web application for managing a personal video game library. Users can register, log in, add games, view their own game list, delete games, and reset their password by email.

**The whole README has been written by AI.**

## Live application

The application is currently hosted at:

[https://softala.haaga-helia.fi:8081/login](https://softala.haaga-helia.fi:8081/login)

## Features

- User registration
- User login with Spring Security
- Separate game library for each registered user
- Add game to your own library
- View your own games
- Delete your own games
- Status selection for each game
- Password reset by email

## Tech stack

- Java 17
- Spring Boot
- Spring MVC
- Thymeleaf
- Spring Data JPA
- Spring Security
- Bean Validation
- H2 database

## Current data model

The main tables/entities in the project are:

- `User`
- `Game`
- `Status`

Relationship used in the app:

- one user can have many games
- each game has one status

## How the app works

- After login, the user is redirected to `/home`
- The user can open `/games` to see only their own games
- `/addgame` opens the form for adding a new game
- `/savegame` saves the submitted game for the logged-in user
- `/deletegame/{id}` deletes a game only if it belongs to the logged-in user
- `/register` is used to create a new account
- `/forgotpassword` and `/resetpassword` are used for password reset

## Demo users

The application seeds two users at startup:

- Username: `user`
- Role: `USER`

- Username: `admin`
- Role: `ADMIN`

The demo data also adds example games to the `user` account.

## Local development

Requirements:

- Java 17
- Maven

Run locally:

```bash
mvn spring-boot:run
```

The application uses H2 in-memory database in the current setup.

## Email reset configuration

Password reset email uses SMTP settings from environment variables in `application.properties`.

Important variables:

- `MAIL_USERNAME`
- `MAIL_PASSWORD`
