package com.ksedu.labsystem.controller;


import com.ksedu.labsystem.pojo.Role;
import com.ksedu.labsystem.service.RoleService;
import com.ksedu.labsystem.utils.ResultOBJ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Xzj
 * @Description
 * @create 2023/10/9 14:57
 */
@RestController
@RequestMapping("/adminapi/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;



    @GetMapping
    public List<Role> getRoleList(){
        return roleService.getRoleList();
    }

    @PutMapping(value = "/{id}")
    public ResultOBJ updateRoleList(@PathVariable Integer id, @RequestBody Role role){
        try {
            role.setId(id);
            roleService.updateRolelist(role);
            return ResultOBJ.UPDATE_SUCCESS;
        } catch (Exception e) {
            return ResultOBJ.UPDATE_ERROR;
        }

    }
    @DeleteMapping(value = "/{id}")
    public ResultOBJ deleteRoleList(@PathVariable Integer id){
        try{
            roleService.deleteRoleList(id);
            return ResultOBJ.DELETE_SUCCESS;
        }catch (Exception e){
            return ResultOBJ.DELETE_ERROR;
        }
    }




}
