package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesService {
    private NoteMapper noteMapper;
    private UserService userService;
    private Utilities utilities;

    public NotesService(NoteMapper noteMapper, UserService userService, Utilities utilities) {
        this.noteMapper = noteMapper;
        this.userService = userService;
        this.utilities = utilities;
    }

    public List<Note> getNotes() {
        return noteMapper.getNotes(utilities.getCurrentUserId());
    }

    public void addNote(Integer noteId, String noteTitle, String noteDescription) {
        noteMapper.addNote(new Note(noteId, noteTitle, noteDescription, utilities.getCurrentUserId()));
    }

    public void editNote(Note note) {
        noteMapper.updateNote(note);
    }

    public void deleteNote(Integer noteId) {
        noteMapper.deleteNote(noteId, utilities.getCurrentUserId());
    }
}
