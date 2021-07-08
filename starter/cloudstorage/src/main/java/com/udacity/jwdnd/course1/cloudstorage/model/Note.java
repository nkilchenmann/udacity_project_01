package com.udacity.jwdnd.course1.cloudstorage.model;

public class Note {
    private Integer userId;
    private Integer noteId;
    private String title;
    private String description;

    public Note(Integer userId, String title, String description) {
        this.userId = userId;
        this.noteId = noteId;
        this.title = title;
        this.description = description;
    }

    public Integer getNoteId() {
        return noteId;
    }

    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
