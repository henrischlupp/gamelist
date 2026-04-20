package com.gamelist.model;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

    Optional<User> findByEmail(String email);

    User findByResetToken(String token);
}
