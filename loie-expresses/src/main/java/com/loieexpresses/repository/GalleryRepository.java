package com.loieexpresses.repository;

import com.loieexpresses.entity.GalleryImage;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface GalleryRepository extends JpaRepository<GalleryImage, Long> {
    List<GalleryImage> findByAlbum(String album);
}
