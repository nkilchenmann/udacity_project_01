package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class NotesService {
    private NoteMapper noteMapper;

    public NotesService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public List<Note> getNotes() {
        //TODO: somehow inject the userid here
        return noteMapper.getNotes(5);
    }

    public void addNote(String noteTitle, String noteDescription) {
        //TODO: somehow inject the userid here
        noteMapper.addNote(new Note(5, noteTitle, noteDescription));
    }

    public void editNote(Note note){
        noteMapper.updateNote(note);
    }

    public void deleteNote(Integer noteId) {
        noteMapper.deleteNote(noteId);
    }
}
