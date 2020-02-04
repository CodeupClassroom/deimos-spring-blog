package com.codeup.deimosspringblog.repositories;

import com.codeup.deimosspringblog.models.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdRepository extends JpaRepository<Ad, Long> {

    List<Ad> findByTitle(String title);


}
