package com.thorton.grant.uspto.prototypewebapp.controllers;
import com.thorton.grant.uspto.prototypewebapp.service.storage.StorageService;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;


@Controller
public class FileServerController {

    private final StorageService storageService;

    public FileServerController(StorageService storageService) {
        this.storageService = storageService;
    }

    ///////////////////////////////////////////////////////////////////////////////
    //serving files
    ///////////////////////////////////////////////////////////////////////////////
    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        //return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
        //"attachment; filename=\"" + file.getFilename() + "\"").body(file);

        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(file);




    }


    @GetMapping("/files-pdf/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> servePDFFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        //return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
        //"attachment; filename=\"" + file.getFilename() + "\"").body(file);

        //return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(file);

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(file);


    }

    @GetMapping("/files-server/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFileDownload(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
        "attachment; filename=\"" + file.getFilename() + "\"").body(file);

        //return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(file);


    }
}
