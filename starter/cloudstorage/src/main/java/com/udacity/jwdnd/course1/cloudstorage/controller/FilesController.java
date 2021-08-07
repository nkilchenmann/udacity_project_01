package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.UploadFile;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class FilesController {
    private FileService fileService;

    public FilesController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/file")
    public String addFile(@RequestParam("fileUpload") MultipartFile multipartFile, Model model) throws IOException {
        if (multipartFile.getOriginalFilename() == "") {
            model.addAttribute("fileUploadStatus", "emptyFileName");
        } else if (multipartFile.getSize() / Math.pow(1024, 2) > 2) {
            model.addAttribute("fileUploadStatus", "tooLarge");
        } else if (fileService.checkFileNameExists(multipartFile.getOriginalFilename())) {
            model.addAttribute("fileUploadStatus", "fileNameAlreadyExists");
        } else {
            try {
                fileService.addFile(multipartFile);
                model.addAttribute("fileUploadStatus", "ok");
            } catch (Exception e) {
                model.addAttribute("fileUploadStatus", "failure");
            }
        }
        return "result";
    }

    @GetMapping("/deleteFile")
    public String deleteFile(@RequestParam(name = "fileId") Integer fileId, Model model) {
        try {
            fileService.deleteFile(fileId);
            model.addAttribute("fileUploadStatus", "ok");
        } catch (Exception e) {
            model.addAttribute("fileUploadStatus", "failure");
        }
        return "result";
    }

    @GetMapping("/downloadFile")
    public ResponseEntity downloadFile(@RequestParam(name = "fileId") Integer fileId) {
        UploadFile uploadFile = fileService.downloadFile(fileId);
        byte[] filedata = uploadFile.getFiledata();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.parseMediaType(uploadFile.getContenttype()));
        return new ResponseEntity(
                filedata,
                httpHeaders,
                HttpStatus.ACCEPTED);
    }
}
