package com.ksedu.labsystem.controller;

import com.ksedu.labsystem.pojo.Lab;
import com.ksedu.labsystem.service.LabService;
import com.ksedu.labsystem.utils.ResultOBJ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Xzj
 * @Description
 * @create 2023/10/11 15:13
 */
@RestController
@RequestMapping("/adminapi/labs")
public class LabController {
    @Autowired
    private LabService labService;
    @GetMapping
    public List<Lab> getLabList(Lab lab) {
        System.out.println(labService.getLabList(lab));
        return labService.getLabList(lab);
    }
    @PostMapping
    public ResultOBJ addLabList(@RequestBody Lab lab){
        try{
            labService.addLabList(lab);
            return ResultOBJ.ADD_SUCCESS;
        }catch (Exception e){
            return ResultOBJ.ADD_ERROR;
        }

    }
    @PutMapping(value="/{id}")
    public ResultOBJ updateLabList(@PathVariable Integer id,@RequestBody Lab lab){
        try{
            lab.setId(id);
            labService.updateLabList(lab);
            return ResultOBJ.UPDATE_SUCCESS;
        }catch (Exception e){
            return ResultOBJ.UPDATE_ERROR;
        }

    }

    @DeleteMapping(value="/{id}")
    public ResultOBJ DeleteLabList(@PathVariable Integer id){
        try{

            labService.deleteLabList(id);
            return ResultOBJ.DELETE_SUCCESS;
        }catch (Exception e){
            return ResultOBJ.DELETE_ERROR;
        }

    }
}
