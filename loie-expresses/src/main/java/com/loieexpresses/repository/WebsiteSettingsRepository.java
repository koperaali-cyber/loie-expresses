package com.loieexpresses.repository;

import com.loieexpresses.entity.WebsiteSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface WebsiteSettingsRepository extends JpaRepository<WebsiteSettings, Long> {
}
