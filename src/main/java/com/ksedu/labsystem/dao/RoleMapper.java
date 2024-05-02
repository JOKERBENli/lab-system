package com.ksedu.labsystem.dao;

import com.ksedu.labsystem.pojo.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Xzj
 * @Description
 * @create 2023/10/9 14:35
 */

@Mapper
public interface RoleMapper {
    public List<Role> getRoleList();

    void updateRolelist(Role role);

    void deleteRoleList(Integer id);
}
