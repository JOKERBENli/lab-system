package com.ksedu.labsystem.controller;

import com.ksedu.labsystem.config.JwtConfig;
import com.ksedu.labsystem.pojo.User;
import com.ksedu.labsystem.service.UserService;
import com.ksedu.labsystem.utils.ResultOBJ;
import com.ksedu.labsystem.utils.SYSConstant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author Xzj
 * @Description
 * @create 2023/10/10 13:32
 */
@RestController
@RequestMapping("/adminapi/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtConfig jwtConfig;

    private static final Logger logger = LogManager.getLogger(UserController.class);

    @GetMapping
    public List<User> getUserList(User user){
        return userService.getUserList(user);
    }

    @PostMapping("/login")
    public ResultOBJ login(@RequestBody User user){
        try{
            List<User> list = userService.getUserList(user);
            if(list.isEmpty()){
                throw new RuntimeException();
            }

            //list.get(0)
            User currentUser = list.get(0);
            String token = jwtConfig.createToken(currentUser.getUsername());
            currentUser.setToken(token);


            logger.info("JWT login check-------");




            return new ResultOBJ(SYSConstant.CODE_SUCCESS,SYSConstant.LOGIN_SUCCESS,currentUser);
        }catch (Exception e){
            return new ResultOBJ(SYSConstant.CODE_ERROR,SYSConstant.LOGIN_ERROR);
        }

    }
    @PostMapping
    public ResultOBJ addUserList(@RequestBody User user){
        try{
            userService.addUserList(user);
            logger.info("---------------addUserList success---------------------");
            return ResultOBJ.ADD_SUCCESS;
        }catch(Exception e){
            return ResultOBJ.ADD_ERROR;
        }
    }

    @PutMapping(value="/{id}")
    public ResultOBJ updateUserList(@PathVariable Integer id,@RequestBody User user){
        try{
            user.setId(id);
            userService.updateUserList(user);
            return ResultOBJ.UPDATE_SUCCESS;
        }catch (Exception e){
            return ResultOBJ.UPDATE_ERROR;
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResultOBJ deleteUserList(@PathVariable Integer id){
        try{
            userService.deleteUserList(id);
            return ResultOBJ.DELETE_SUCCESS;
        }catch (Exception e){
            return ResultOBJ.DELETE_ERROR;
        }
    }



}
