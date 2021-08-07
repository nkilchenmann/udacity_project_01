package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.UploadFile;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomeController {
    private NotesService notesService;
    private CredentialService credentialService;
    private FileService fileService;

    public HomeController(NotesService notesService, CredentialService credentialService, FileService fileService) {
        this.notesService = notesService;
        this.credentialService = credentialService;
        this.fileService = fileService;
    }

    @GetMapping({"/", "/home"})
    public String getHomePage(
            @ModelAttribute("Note") Note note,
            @ModelAttribute("Credential") Credential credential,
            @ModelAttribute("UploadFile") UploadFile uploadFile,
            Model model) {

        model.addAttribute("notes", this.notesService.getNotes());
        model.addAttribute("credentials", this.credentialService.getCredentials());
        model.addAttribute("uploadFiles", this.fileService.getFileList());
        return "home";

    }
}
