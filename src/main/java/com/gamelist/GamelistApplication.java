package com.gamelist;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.gamelist.model.Game;
import com.gamelist.model.GameRepository;
import com.gamelist.model.Status;
import com.gamelist.model.StatusRepository;
import com.gamelist.model.User;
import com.gamelist.model.UserRepository;

@SpringBootApplication
public class GamelistApplication {
    private static final Logger log = LoggerFactory.getLogger(GamelistApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(GamelistApplication.class, args);
    }

    @Bean
    public CommandLineRunner gamedemo(
            GameRepository grepository,
            StatusRepository srepository,
            UserRepository urepository) {
        return (args) -> {
            log.info("Adding statuses if they do not exist");

            Status completed = getOrCreateStatus(srepository, "Completed");
            Status unfinished = getOrCreateStatus(srepository, "Unfinished");
            Status playable = getOrCreateStatus(srepository, "Playable");

            if (urepository.findByUsername("user") == null) {
                User user1 = new User(
                        "user@gmail.com",
                        "user",
                        "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6",
                        "USER");
                urepository.save(user1);
            }

            if (urepository.findByUsername("admin") == null) {
                User user2 = new User(
                        "admin@gmail.com",
                        "admin",
                        "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C",
                        "ADMIN");
                urepository.save(user2);
            }

            User normalUser = urepository.findByUsername("user");

            log.info("Adding games for demo user if they do not exist");

            saveGameIfNotExists(grepository, "Counter-Strike 2", "Valve", "PC", "Premier and matchmaking", LocalDate.of(2023, 9, 27), completed, normalUser);
            saveGameIfNotExists(grepository, "Terraria", "Re-Logic", "PC", "Building and boss progression", LocalDate.of(2011, 5, 16), playable, normalUser);
            saveGameIfNotExists(grepository, "Minecraft", "Mojang", "PC", "Modded survival world", LocalDate.of(2011, 11, 18), playable, normalUser);
            saveGameIfNotExists(grepository, "The Witcher 3: Wild Hunt", "CD Projekt Red", "PC", "Main story in progress", LocalDate.of(2015, 5, 19), unfinished, normalUser);
            saveGameIfNotExists(grepository, "Red Dead Redemption 2", "Rockstar Games", "PC", "Story mode playthrough", LocalDate.of(2018, 10, 26), unfinished, normalUser);
            saveGameIfNotExists(grepository, "Grand Theft Auto V", "Rockstar Games", "PC", "Finished campaign", LocalDate.of(2013, 9, 17), completed, normalUser);
            saveGameIfNotExists(grepository, "Cyberpunk 2077", "CD Projekt Red", "PC", "Exploring Night City", LocalDate.of(2020, 12, 10), playable, normalUser);
            saveGameIfNotExists(grepository, "Stardew Valley", "ConcernedApe", "PC", "Farm expansion run", LocalDate.of(2016, 2, 26), playable, normalUser);
            saveGameIfNotExists(grepository, "Elden Ring", "FromSoftware", "PC", "NG+ run ongoing", LocalDate.of(2022, 2, 25), unfinished, normalUser);
            saveGameIfNotExists(grepository, "Portal 2", "Valve", "PC", "Completed co-op and single-player", LocalDate.of(2011, 4, 19), completed, normalUser);
        };
    }

    private Status getOrCreateStatus(StatusRepository srepository, String statusName) {
        Status existingStatus = srepository.findByStatusNameIgnoreCase(statusName);
        if (existingStatus != null) {
            return existingStatus;
        }
        return srepository.save(new Status(statusName));
    }

    private void saveGameIfNotExists(
            GameRepository grepository,
            String title,
            String developer,
            String platform,
            String notes,
            LocalDate releaseDate,
            Status status,
            User user) {
        if (!grepository.existsByTitleAndDeveloperAndPlatformAndNotesAndReleaseDateAndStatusAndUser(
                title,
                developer,
                platform,
                notes,
                releaseDate,
                status,
                user)) {
            Game game = new Game(title, developer, notes, releaseDate, status, platform);
            game.setUser(user);
            grepository.save(game);
        }
    }
}
