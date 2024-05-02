package com.ksedu.labsystem.controller;

import com.ksedu.labsystem.service.UploadService;
import com.ksedu.labsystem.utils.ResultOBJ;
import com.ksedu.labsystem.utils.SYSConstant;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.apache.logging.log4j.Logger;
import java.io.FileNotFoundException;

/**
 * @author Xzj
 * @Description
 * @create 2024/2/27 23:42
 */
@RequestMapping("/adminapi/upload")
@RestController
public class UploadController {

    @Autowired
    private UploadService uploadService;
    private static final Logger logger = LogManager.getLogger(UserController.class);


    @PostMapping
    public ResultOBJ upload(@RequestParam("file") MultipartFile file){
        try {
            uploadService.upload(file);
            logger.info("upload test-------");
            return new ResultOBJ(SYSConstant.CODE_SUCCESS,"上传成功");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
