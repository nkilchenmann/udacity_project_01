package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.UploadFile;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.print.attribute.standard.Media;
import java.io.IOException;

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

    @GetMapping("/home")
    public String getHomePage(@ModelAttribute("Note") Note note, @ModelAttribute("Credential") Credential credential, @ModelAttribute("UploadFile") UploadFile uploadFile, Model model) {
        model.addAttribute("notes", this.notesService.getNotes());
        model.addAttribute("credentials", this.credentialService.getCredentials());
        model.addAttribute("uploadFiles", this.fileService.getFiles());
        return "home";
    }

    @PostMapping("/note")
    public String addNote(@ModelAttribute("Note") Note note) {
        if (note.getNoteId() != null) {
            notesService.editNote(note);
        } else {
            notesService.addNote(note.getTitle(), note.getDescription());
        }
        return "redirect:/home";
    }

    @GetMapping("/deleteNote")
    public String deleteNote(@RequestParam(name = "id") Integer noteId) {
        //TODO: pass on userId for database check before modification (context.userid or something)
        notesService.deleteNote(noteId);
        return "redirect:/home";
    }

    @PostMapping("/credential")
    public String addCredential(@ModelAttribute("Credential") Credential credential) {
        if (credential.getCredentialId() != null) {
            credentialService.updateCredential(credential);
        } else {
            credentialService.addCredential(credential);
        }
        return "redirect:/home";
    }

    @GetMapping("/deleteCredential")
    public String deleteCredential(@RequestParam(name = "id") Integer credentialId) {
        //TODO: pass on userId for database check before modification (context.userid or something)
        credentialService.deleteCredential(credentialId);
        return "redirect:/home";
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
