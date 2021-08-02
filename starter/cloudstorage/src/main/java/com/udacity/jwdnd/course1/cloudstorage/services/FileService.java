package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.UploadFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

//TODO: if filename already exists --> don't upload and show error message
//TODO: fileupload possible without selecting a file
@Service
public class FileService {
    private FileMapper fileMapper;
    private Utilities utilities;

    public FileService(FileMapper fileMapper, Utilities utilities) {
        this.fileMapper = fileMapper;
        this.utilities = utilities;
    }

    public List<UploadFile> getFiles() {
        return fileMapper.getFiles(utilities.getCurrentUserId());
    }

    public void addFile(MultipartFile multipartFile) throws IOException {
        fileMapper.addFile(new UploadFile(
                null,
                multipartFile.getOriginalFilename(),
                multipartFile.getContentType(),
                String.valueOf(multipartFile.getSize()),
                utilities.getCurrentUserId(),
                multipartFile.getBytes()
        ));
    }

    public void deleteFile(Integer fileId) {
        fileMapper.deleteFile(fileId, utilities.getCurrentUserId());
    }

    public UploadFile getFile(Integer fileId) {
        return fileMapper.getFile(fileId, utilities.getCurrentUserId());
    }
}
