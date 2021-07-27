package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.UploadFile;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class FilesController {
    private FileService fileService;

    public FilesController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/file")
    public String addFile(@RequestParam("fileUpload") MultipartFile multipartFile) throws IOException {
        fileService.addFile(multipartFile);
        return "redirect:/home";
    }

    @GetMapping("/deleteFile")
    public String deleteFile(@RequestParam(name = "fileId") Integer fileId) {
        fileService.deleteFile(fileId);
        return "redirect:/home";
    }

    @GetMapping("/downloadFile")
    public ResponseEntity downloadFile(@RequestParam(name = "fileId") Integer fileId) {
        UploadFile uploadFile = fileService.getFile(fileId);
        byte[] filedata = uploadFile.getFiledata();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.parseMediaType(uploadFile.getContenttype()));
        return new ResponseEntity(
                filedata,
                httpHeaders,
                HttpStatus.ACCEPTED);
    }
}
