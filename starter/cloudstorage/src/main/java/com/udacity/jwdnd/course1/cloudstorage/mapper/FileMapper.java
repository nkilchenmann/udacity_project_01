package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.UploadFile;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {
    @Select("SELECT * FROM FILES WHERE userid=#{userId}")
    List<UploadFile> getFileList(Integer userId);

    @Select("SELECT * FROM FILES WHERE fileId=#{fileId} AND userid=#{userId}")
    UploadFile downloadFile(Integer fileId, Integer userId);

    @Select("SELECT * FROM FILES WHERE filename=#{fileName} AND userid=#{userId}")
    List<UploadFile> checkFileNameExists(String fileName, Integer userId);

    @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) VALUES (#{filename},#{contenttype},#{filesize},#{userid},#{filedata})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int addFile(UploadFile uploadFile);

    @Delete("DELETE FROM FILES WHERE fileId=#{fileId} AND userid=#{userId}")
    void deleteFile(Integer fileId, Integer userId);
}