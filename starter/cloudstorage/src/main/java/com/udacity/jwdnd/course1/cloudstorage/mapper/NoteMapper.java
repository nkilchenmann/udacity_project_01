package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {
    @Select("SELECT * FROM NOTES WHERE userid=#{userId}")
    List<Note> getNotes(Integer userId);

    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES(#{title}, #{description}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int addNote(Note note);

    @Update("UPDATE NOTES set noteTitle=#{title}, notedescription=#{description} WHERE userid=#{userId} AND noteId=#{noteId}")
    void updateNote(Note note);

    @Delete("DELETE FROM NOTES WHERE userid=#{userId} AND noteid=#{noteId}")
    void deleteNote(Integer noteId, Integer userId);

}
