package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {

    @Select("SELECT * FROM CREDENTIALS WHERE userid=#{userId}")
    List<Credential> getCredentials(Integer userId);

    @Insert("INSERT INTO CREDENTIALS (userid, username, url, password, key) VALUES (#{userId}, #{username}, #{url}, #{password}, #{key})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int addCredential(Credential credential);

    @Update("UPDATE CREDENTIALS set username=#{username}, url=#{url}, password=#{password}, key=#{key} WHERE userid=#{userId} AND credentialId=#{credentialId}")
    void updateCredential(Credential credential);

    @Delete("DELETE FROM CREDENTIALS WHERE userid=#{userId} AND credentialId=#{credentialId}")
    void deleteCredential(Integer credentialId, Integer userId);
}