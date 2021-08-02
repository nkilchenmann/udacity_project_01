package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NotesController {
    private NotesService notesService;

    public NotesController(NotesService notesService) {
        this.notesService = notesService;
    }

    @PostMapping("/note")
    public String addNote(@ModelAttribute("Note") Note note) {
        if (note.getNoteId() != null) {
            notesService.editNote(note);
        } else {
            notesService.addNote(null, note.getTitle(), note.getDescription());
        }
        return "redirect:/home";
    }

    @GetMapping("/deleteNote")
    public String deleteNote(@RequestParam(name = "noteId") Integer noteId) {
        notesService.deleteNote(noteId);
        return "redirect:/home";
    }
}
