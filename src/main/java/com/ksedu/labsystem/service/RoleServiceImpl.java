package com.ksedu.labsystem.service;


import com.ksedu.labsystem.dao.RoleMapper;
import com.ksedu.labsystem.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Xzj
 * @Description
 * @create 2023/10/9 14:54
 */
@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> getRoleList() {
        return roleMapper.getRoleList();
    }

    @Override
    public void updateRolelist(Role role) {
        roleMapper.updateRolelist(role);
    }

    @Override
    public void deleteRoleList(Integer id) {
        roleMapper.deleteRoleList(id);
    }
}
