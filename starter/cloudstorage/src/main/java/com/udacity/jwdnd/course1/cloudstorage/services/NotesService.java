package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotesService {
    private NoteMapper noteMapper;
    private List<Note> noteList;

    public NotesService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
        this.noteList = new ArrayList<>();
    }

    public List<Note> getNotes(){
        /*List<Note> noteList = new ArrayList<>();
        Note noteOne = new Note(1,"noteOne", "This is my first note");
        Note noteTwo = new Note(2, "noteTwo", "This is my second note");
        Note noteThree = new Note(3, "Doggo", "Ruby");
        noteList.add(noteOne);
        noteList.add(noteTwo);
        noteList.add(noteThree);*/
        return noteList;
    }

    public void addNote(String noteTitle, String noteDescription){
        Note note = new Note(5,noteTitle,noteDescription);
        noteList.add(note);
        return;
    }
}
