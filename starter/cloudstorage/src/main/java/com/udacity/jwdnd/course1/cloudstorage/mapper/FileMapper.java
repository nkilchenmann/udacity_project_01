package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.UploadFile;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {
    @Select("SELECT * FROM FILES WHERE userid=#{userId}")
    List<UploadFile> getFiles(Integer userId);

    @Select("SELECT * FROM FILES WHERE fileId=#{fileId} AND userid=#{userId}")
    UploadFile getFile(Integer fileId, Integer userId);

    @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) VALUES (#{filename},#{contenttype},#{filesize},#{userid},#{filedata})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int addFile(UploadFile uploadFile);

    @Delete("DELETE FROM FILES WHERE fileId=#{fileId} AND userid=#{userId}")
    void deleteFile(Integer fileId, Integer userId);
}