package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.UploadFile;
import org.apache.ibatis.annotations.*;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Mapper
public interface FileMapper {
    @Select("SELECT * FROM FILES")
    List<UploadFile> getFiles();

    @Select("SELECT * FROM FILES WHERE fileId=#{fileId}")
    UploadFile getFile(Integer fileId);

    @Insert("INSERT INTO FILES (filename, contenttype, filesize, filedata) VALUES (#{filename},#{contenttype},#{filesize},#{filedata})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int addFile(UploadFile uploadFile);

    @Delete("DELETE FROM FILES WHERE fileId=#{fileId}")
    void deleteFile(Integer fileId);
}