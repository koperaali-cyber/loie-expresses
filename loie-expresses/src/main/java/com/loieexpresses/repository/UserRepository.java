package com.loieexpresses.repository;

import com.loieexpresses.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByResetToken(String token);
    boolean existsByUsername(String username);
}
