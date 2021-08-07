package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String addNote(@ModelAttribute("Note") Note note, Model model) {
        if (note.getNoteId() != null) {
            try {
                notesService.editNote(note);
                model.addAttribute("noteUploadStatus", "ok");
            } catch (Exception e) {
                model.addAttribute("noteUploadStatus", "failure");
            }
        } else {
            try {
                notesService.addNote(null, note.getTitle(), note.getDescription());
                model.addAttribute("noteUploadStatus", "ok");
            } catch (Exception e) {
                model.addAttribute("noteUploadStatus", "failure");
            }
        }
        return "result";
    }

    @GetMapping("/deleteNote")
    public String deleteNote(@RequestParam(name = "noteId") Integer noteId, Model model) {
        try {
            notesService.deleteNote(noteId);
            model.addAttribute("noteUploadStatus", "ok");
        } catch (Exception e) {
            model.addAttribute("noteUploadStatus", "failure");
        }
        return "result";
    }
}
