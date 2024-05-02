package com.ksedu.labsystem.service;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Xzj
 * @Description
 * @create 2024/2/27 23:24
 */
@Service
public class UploadServiceImpl implements UploadService{

    @Override
    public void upload(MultipartFile file) throws FileNotFoundException {
        String fileName = "bg.jpg";
        String filepath = ResourceUtils.getURL("classpath:").getPath()+"static/upload/";
        System.out.println(filepath);
        File dist = new File(filepath+fileName);
        if(!dist.getParentFile().exists()){
            dist.getParentFile().mkdirs();
        }


        try {
            file.transferTo(dist);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
