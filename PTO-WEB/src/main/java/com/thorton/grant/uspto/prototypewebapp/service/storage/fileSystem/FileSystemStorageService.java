package com.thorton.grant.uspto.prototypewebapp.service.storage.fileSystem;

import com.thorton.grant.uspto.prototypewebapp.service.storage.StorageService;
import com.thorton.grant.uspto.prototypewebapp.service.storage.error.StorageException;
import com.thorton.grant.uspto.prototypewebapp.service.storage.error.StorageFileNotFoundException;
import com.thorton.grant.uspto.prototypewebapp.service.storage.properties.StorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class FileSystemStorageService implements StorageService {

    private final Path rootLocation;

    private String rootPath;

    private int fileCounter = 0;

    @Autowired
    public FileSystemStorageService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());

        this.rootPath = properties.getLocation();
    }

    @Override
    public int getCounter() {
        return 0;
    }

    @Override
    public String store(MultipartFile file) {
        String newFileName = "";
        try {
            //if (file.isEmpty()) {
            //   throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
            //}
            newFileName = fileCounter+file.getOriginalFilename();
            Files.copy(file.getInputStream(), this.rootLocation.resolve(newFileName));
            fileCounter++;

            //Path source = this.rootLocation.resolve(file.getOriginalFilename());
            //Files.move(source, source.resolveSibling(filePreFix+"-attorney-bar-credentials.jpg"));

        } catch (IOException e) {
            throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
        }
        return newFileName;
    }

    @Override
    public String storeFile(File file, String fileName) {
        String newFileName = "";
        try {
            //if (file.isEmpty()) {
            //   throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
            //}
            newFileName = fileCounter+fileName;
            InputStream targetStream = new FileInputStream(file);
            Files.copy(targetStream, this.rootLocation.resolve(newFileName));
            fileCounter++;

            //Path source = this.rootLocation.resolve(file.getOriginalFilename());
            //Files.move(source, source.resolveSibling(filePreFix+"-attorney-bar-credentials.jpg"));

        } catch (IOException e) {
            throw new StorageException("Failed to store file " + fileName, e);
        }
        return newFileName;
    }


    public String storeBW(MultipartFile file) {

        String newFileName = "";
        try {

            BufferedImage image = ImageIO.read(file.getInputStream());
/*
            BufferedImage result = new BufferedImage(
                    image.getWidth(),
                    image.getHeight(),
                    BufferedImage.TYPE_BYTE_BINARY);

            Graphics2D graphic = result.createGraphics();
            graphic.drawImage(image, 0, 0, Color.WHITE, null);
            graphic.dispose();

            File output = new File(file.getOriginalFilename());
            ImageIO.write(result, "png", output);
            InputStream targetStream = new FileInputStream(output);
            newFileName = fileCounter+"bw_"+file.getOriginalFilename();

*/


            ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
            ColorConvertOp op = new ColorConvertOp(cs, null);
            BufferedImage imageGray = op.filter(image, null);

            File output = new File(file.getOriginalFilename());
            ImageIO.write(imageGray, "png", output);
            InputStream targetStream = new FileInputStream(output);
            newFileName = fileCounter+"bw_"+file.getOriginalFilename();


            Files.copy(targetStream, this.rootLocation.resolve(newFileName));
            fileCounter++;


        } catch (IOException e) {
            throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
        }

        return newFileName;
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(path -> this.rootLocation.relativize(path));
        } catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    @Override
    public Path load(String filename) {

        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException("Could not read file: " + filename);

            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void init() {
        try {
            Files.createDirectory(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    @Override
    public String getRootPath() {
        return this.rootPath;
    }
}
