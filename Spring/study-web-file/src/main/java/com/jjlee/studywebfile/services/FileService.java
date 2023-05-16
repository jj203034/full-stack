package com.jjlee.studywebfile.services;

import com.jjlee.studywebfile.entities.FileEntity;
import com.jjlee.studywebfile.mappers.FileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;


// Remote => 원격
@Service
public class FileService {
    private final FileMapper fileMapper;

    @Autowired
    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public boolean putFile(HttpServletRequest request, MultipartFile multipartFile) throws IOException {
        FileEntity file = new FileEntity()
                .setCreatedAt(new Date())
                .setClientIp(request.getRemoteAddr())
                .setClientUa(request.getHeader("User-Agent"))
                .setName(multipartFile.getOriginalFilename())
                .setSize((int) multipartFile.getSize())
                .setContentType(multipartFile.getContentType())
                .setData(multipartFile.getBytes());
        return this.fileMapper.insertFile(file) > 0;
    }

    public boolean putMultipleFile(HttpServletRequest request, MultipartFile[] multipartFiles) throws IOException {
        int inserted = 0;
        for (int i = 0; i < multipartFiles.length; i++) {
            FileEntity file = new FileEntity()
                    .setCreatedAt(new Date())
                    .setClientIp(request.getRemoteAddr())
                    .setClientUa(request.getHeader("User-Agent"))
                    .setName(multipartFiles[i].getOriginalFilename())
                    .setSize((int) multipartFiles[i].getSize())
                    .setContentType(multipartFiles[i].getContentType())
                    .setData(multipartFiles[i].getBytes());
            inserted = fileMapper.insertFile(file);
        }
        return multipartFiles.length == inserted;
    }
}
