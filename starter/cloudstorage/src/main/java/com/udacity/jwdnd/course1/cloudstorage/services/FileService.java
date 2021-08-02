package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.UploadFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

//TODO: if filename already exists --> don't upload and show error message
//TODO: username database requests
//TODO: fileupload possible without selecting a file
@Service
public class FileService {
    private FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public List<UploadFile> getFiles() {
        return fileMapper.getFiles();
    }

    public void addFile(MultipartFile multipartFile) throws IOException {
        UploadFile uploadFile = new UploadFile();
        uploadFile.setFiledata(multipartFile.getBytes());
        uploadFile.setFilename(multipartFile.getOriginalFilename());
        uploadFile.setContenttype(multipartFile.getContentType());
        uploadFile.setUserid(5);
        uploadFile.setFilesize(String.valueOf(multipartFile.getSize()));
        fileMapper.addFile(uploadFile);
    }

    public void deleteFile(Integer fileId) {
        fileMapper.deleteFile(fileId);
    }

    public UploadFile getFile(Integer fileId) {
        UploadFile myfile = fileMapper.getFile(fileId);
        return fileMapper.getFile(fileId);
    }
}
