package com.ksedu.labsystem.controller;

import com.ksedu.labsystem.pojo.Right;
import com.ksedu.labsystem.service.RightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Xzj
 * @Description
 * @create 2023/10/5 10:16
 */

@RestController
@RequestMapping("/adminapi/rights")
public class RightController {
    @Autowired
    private RightService rightService;

    @GetMapping
    public List<Right> getRightList(){
        return rightService.getRightList();
    }
}
