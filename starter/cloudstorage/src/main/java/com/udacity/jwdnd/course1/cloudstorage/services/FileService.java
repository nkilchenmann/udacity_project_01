package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.UploadFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileService {
    private FileMapper fileMapper;
    private Utilities utilities;

    public FileService(FileMapper fileMapper, Utilities utilities) {
        this.fileMapper = fileMapper;
        this.utilities = utilities;
    }

    public List<UploadFile> getFileList() {
        return fileMapper.getFileList(utilities.getCurrentUserId());
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

    public UploadFile downloadFile(Integer fileId) {
        return fileMapper.downloadFile(fileId, utilities.getCurrentUserId());
    }

    public boolean checkFileNameExists(String fileName) {
        if (fileMapper.checkFileNameExists(fileName, utilities.getCurrentUserId()).isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}
