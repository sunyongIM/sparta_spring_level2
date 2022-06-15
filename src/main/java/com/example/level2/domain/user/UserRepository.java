package com.example.level2.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean findByEmailAndPassword(String email, String pwd);

    Optional<User> findByEmail(String email);
}
