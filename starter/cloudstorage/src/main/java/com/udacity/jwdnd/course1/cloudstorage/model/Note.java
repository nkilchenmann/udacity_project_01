package com.udacity.jwdnd.course1.cloudstorage.model;

public class Note {
    private Integer userid;
    private Integer noteid;
    private String title;
    private String description;

    public Note(Integer userid, String title, String description) {
        this.userid = userid;
        this.noteid = noteid;
        this.title = title;
        this.description = description;
    }

    public Integer getNoteId() {
        return noteid;
    }

    public void setNoteId(Integer noteId) {
        this.noteid = noteId;
    }

    public Integer getUserId() {
        return userid;
    }

    public void setUserId(Integer userId) {
        this.userid = userId;
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
