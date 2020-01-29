package com.codeup.deimosspringblog.repositories;

import com.codeup.deimosspringblog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}