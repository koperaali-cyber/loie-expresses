package com.loieexpresses.repository;

import com.loieexpresses.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findByPublishedTrueOrderByCreatedAtDesc();
    Optional<News> findBySlug(String slug);
}
