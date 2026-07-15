package com.example.myJar;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<AuthUsers, Integer> {
    Optional<AuthUsers> findByEmail (String email);
}
