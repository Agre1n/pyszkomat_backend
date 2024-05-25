package net.springboot.pyszkomat_backend.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageService {

    private final ResourceLoader resourceLoader;

    public ImageService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public String storeImage(MultipartFile file) throws IOException {
        Path imageDirPath = Paths.get(resourceLoader.getResource("classpath:images/").getURI());
        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();

        System.out.println(imageDirPath.resolve(filename));

        Files.copy(file.getInputStream(), imageDirPath.resolve(filename));
        return filename;
    }

    public Resource loadImage(String filename) {
        Resource resource = resourceLoader.getResource("classpath:images/" + filename);
        if (resource.exists() || resource.isReadable()) {
            return resource;
        } else {
            throw new RuntimeException("Could not read file: " + filename);
        }
    }
}

