package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {

   @Select("SELECT * FROM CREDENTIALS")
    List<Credential> getCredentials();

    @Insert("INSERT INTO CREDENTIALS (userid, username, url, password, key) VALUES (#{userId}, #{username}, #{url}, #{password}, #{key})")
    @Options(useGeneratedKeys = true,keyProperty = "credentialId")
    int addCredential(Credential credential);
}