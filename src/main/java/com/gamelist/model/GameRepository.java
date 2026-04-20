package com.gamelist.model;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface GameRepository extends CrudRepository<Game, Long> {

    boolean existsByTitleAndDeveloperAndPlatformAndNotesAndReleaseDateAndStatusAndUser(
            String title,
            String developer,
            String platform,
            String notes,
            java.time.LocalDate releaseDate,
            Status status,
            User user);

    List<Game> findByUser(User user);

    java.util.Optional<Game> findByIdAndUser(Long id, User user);
}
