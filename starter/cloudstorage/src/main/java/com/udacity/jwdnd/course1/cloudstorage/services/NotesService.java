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
    private List<Note> noteList;

    public NotesService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
        this.noteList = new ArrayList<>();
    }

    public List<Note> getNotes() {
        //TODO: somehow inject the userId here
        noteList = noteMapper.getNotes(5);
        return noteList;
    }

    public void addNote(String noteTitle, String noteDescription) {
        Note note = new Note(5, noteTitle, noteDescription);
        noteMapper.addNote(note);
    }

    public void deleteNote(Integer noteId) {
        Predicate<Note> predicate = note -> note.getNoteId() == 5;
        noteList.removeIf(predicate);
    }
}
