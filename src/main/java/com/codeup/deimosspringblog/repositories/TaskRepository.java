package com.codeup.deimosspringblog.repositories;

import com.codeup.deimosspringblog.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Long> {
}
