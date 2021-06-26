package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {
    @Select("SELECT * FROM NOTES")
    List<Note> getNotes(Integer userId);

    @Insert("Insert into NOTES(notetitle, notedescription, userid) values(#{title}, #{description}, #{userid})")
    @Options(useGeneratedKeys = true,keyProperty = "noteid")
    int addNote(Note note);

    @Delete("DELETE FROM NOTES WHERE noteid=#{id}")
    void deleteNote(Integer id);

}
