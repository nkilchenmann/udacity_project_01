package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    @PostMapping("/note")
    public String postNote(@ModelAttribute("Note") Note note) {
        if (note.getNoteId() != null) {
            System.out.println("***DEBUG***: updating note: " + note.getNoteId().toString());
            notesService.editNote(note);
        } else {
            System.out.println("***DEBUG***: creating a new note");
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
}
