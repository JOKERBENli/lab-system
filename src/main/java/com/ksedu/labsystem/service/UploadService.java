package com.ksedu.labsystem.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

/**
 * @author Xzj
 * @Description
 * @create 2024/2/27 23:21
 */
public interface UploadService {
    public void upload(MultipartFile file) throws FileNotFoundException;
}
