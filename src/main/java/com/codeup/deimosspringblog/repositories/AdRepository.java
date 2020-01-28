package com.codeup.deimosspringblog.repositories;

import com.codeup.deimosspringblog.models.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdRepository extends JpaRepository<Ad, Long> {

    List<Ad> findByOrderByTitle();

    // example of derived query
    Ad findByTitle(String title);

    // examples using JPQL / HQL
    @Query("from Ad a where a.id like ?1")
    Ad getAdById(long id);

    @Query("select title from Ad where LENGTH(title) < 10")
    List<String> getAdsOfCertainTitleLength();

    // using a native query
    @Query(nativeQuery = true, value = "SELECT title FROM ads WHERE LENGTH(title) < 10")
    List<String> getAdsOfCertainTitleLengthNative();


}
