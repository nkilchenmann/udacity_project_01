package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    private NotesService notesService;
    private CredentialService credentialService;

    public HomeController(NotesService notesService, CredentialService credentialService) {
        this.notesService = notesService;
        this.credentialService = credentialService;
    }

    @GetMapping("/home")
    public String getHomePage(@ModelAttribute("Note") Note note, @ModelAttribute("Credential") Credential credential, Model model) {
        model.addAttribute("notes", this.notesService.getNotes());
        model.addAttribute("credentials", this.credentialService.getCredentials());
        return "home";
    }

    @PostMapping("/note")
    public String postNote(@ModelAttribute("Note") Note note) {
        if (note.getNoteId() != null) {
            notesService.editNote(note);
        } else {
            notesService.addNote(note.getTitle(), note.getDescription());
        }
        return "redirect:/home";
    }

    @GetMapping("/deleteNote")
    public String deleteNote(@RequestParam(name = "id") Integer noteId, @ModelAttribute("Note") Note note) {
        //TODO: pass on userId for database check before modification (context.userid or something)
        notesService.deleteNote(noteId);
        return "redirect:/home";
    }

    @PostMapping("/credential")
    public String postCredential(@ModelAttribute("Credential") Credential credential) {
        if (credential.getCredentialId() != null) {
            credentialService.updateCredential(credential);
        } else {
            credentialService.addCredential(credential);
        }
        return "redirect:/home";
    }

    @GetMapping("/deleteCredential")
    public String deleteCredential(@RequestParam(name = "id") Integer credentialId, @ModelAttribute("Credential") Note credential) {
        //TODO: pass on userId for database check before modification (context.userid or something)
        credentialService.deleteCredential(credentialId);
        return "redirect:/home";
    }

}
