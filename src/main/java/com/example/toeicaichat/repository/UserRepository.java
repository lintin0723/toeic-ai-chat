package com.example.toeicaichat.repository;

import com.example.toeicaichat.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    Boolean existsByUsername(String username);

    // This method is required by AuthController for registration checks.
    Boolean existsByEmail(String email);
}
