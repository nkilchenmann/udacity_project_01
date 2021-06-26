package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {
    private NotesService notesService;

    public HomeController(NotesService notesService) {
        this.notesService = notesService;
    }

    @GetMapping("/home")
    public String getHomePage(@ModelAttribute("Note") Note note, Model model) {
        model.addAttribute("notes", this.notesService.getNotes());
        return "home";
    }

    @PostMapping("/home")
    public String postNote(@ModelAttribute("Note") Note note, Model model) {
        notesService.addNote(note.getTitle(), note.getDescription());
        model.addAttribute("notes", this.notesService.getNotes());
        System.out.println("test");
        return "home";
    }

    @GetMapping("/deleteNote")
    public String deleteNote(@ModelAttribute("Note") Note note, Model model){
        notesService.deleteNote(5);
        return "home";
    }

}
