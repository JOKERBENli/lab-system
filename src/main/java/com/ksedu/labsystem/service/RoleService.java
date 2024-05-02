package com.ksedu.labsystem.service;

import com.ksedu.labsystem.pojo.Role;

import java.util.List;

/**
 * @author Xzj
 * @Description
 * @create 2023/10/9 14:43
 */
public interface RoleService {
    public List<Role> getRoleList();

    void updateRolelist(Role role);

    void deleteRoleList(Integer id);
}
