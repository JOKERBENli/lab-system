package com.ksedu.labsystem.service;

import com.ksedu.labsystem.pojo.User;

import java.util.List;

/**
 * @author Xzj
 * @Description
 * @create 2023/10/10 12:40
 */
public interface UserService {
    public List<User> getUserList(User user);

    void addUserList(User user);

    void updateUserList(User user);

    void deleteUserList(Integer id);
}
