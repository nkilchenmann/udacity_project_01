package com.udacity.jwdnd.course1.cloudstorage.model;

public class Note {
    private Integer id;
    private String title;
    private String description;
    //TODO: fix userid
    private final Integer userid = 15;

    public Note(Integer id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
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
