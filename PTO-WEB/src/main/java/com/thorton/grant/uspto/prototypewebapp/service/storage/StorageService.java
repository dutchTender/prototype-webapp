package com.thorton.grant.uspto.prototypewebapp.service.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

    void init();

    String store(MultipartFile file);
    String storeFile(File file, String fileName);

    String storeBW(MultipartFile file);

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);


    String getRootPath();

    void deleteAll();

    int getCounter();

}
