package com.loieexpresses.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

/** Handles saving uploaded images to the local uploads directory. */
@Service
public class FileStorageService {

    @Value("${app.upload.dir:uploads}")
    private String uploadDir;

    /** Saves a file and returns the stored file name (or null if empty). */
    public String store(MultipartFile file) {
        if (file == null || file.isEmpty()) return null;
        try {
            Path dir = Paths.get(uploadDir);
            Files.createDirectories(dir);
            String original = Path.of(file.getOriginalFilename() == null ? "file" : file.getOriginalFilename())
                    .getFileName().toString();
            String ext = original.contains(".") ? original.substring(original.lastIndexOf('.')) : "";
            String name = UUID.randomUUID().toString().replace("-", "") + ext;
            Files.copy(file.getInputStream(), dir.resolve(name), StandardCopyOption.REPLACE_EXISTING);
            return name;
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file: " + e.getMessage(), e);
        }
    }

    public void delete(String fileName) {
        if (fileName == null) return;
        try { Files.deleteIfExists(Paths.get(uploadDir).resolve(fileName)); }
        catch (IOException ignored) { }
    }
}
