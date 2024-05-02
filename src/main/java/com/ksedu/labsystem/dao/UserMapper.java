package com.ksedu.labsystem.dao;

import com.ksedu.labsystem.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Xzj
 * @Description
 * @create 2023/10/10 11:28
 */
@Mapper
public interface UserMapper {
    public List<User> getUserList(User user);

    void addUserList(User user);

    void updateUserList(User user);

    void deleteUserList(Integer id);
}
