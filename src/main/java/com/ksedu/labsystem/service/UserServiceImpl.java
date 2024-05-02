package com.ksedu.labsystem.service;

import com.ksedu.labsystem.dao.UserMapper;
import com.ksedu.labsystem.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Xzj
 * @Description
 * @create 2023/10/10 13:16
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> getUserList(User user) {
        return userMapper.getUserList(user);
    }

    @Override
    public void addUserList(User user) {
        userMapper.addUserList(user);
    }

    @Override
    public void updateUserList(User user) {
        userMapper.updateUserList(user);
    }

    @Override
    public void deleteUserList(Integer id) {
        userMapper.deleteUserList(id);
    }
}
