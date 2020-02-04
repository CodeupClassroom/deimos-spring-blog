package com.codeup.deimosspringblog.repositories;

import com.codeup.deimosspringblog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
