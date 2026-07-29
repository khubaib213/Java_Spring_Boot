package com.example.myJar;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByNameContaining(String name);

    @Query("SELECT u FROM User u WHERE u.name LIKE %:keyword%")
    List<User> searchByName(String name);

    Optional<User> findByEmail (String email);

    @Query("SELECT u FROM User u WHERE u.email LIKE %:domain%")
    List<User> findByEmailDomain(@Param("domain")String domain);
}
