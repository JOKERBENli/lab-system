package com.ksedu.labsystem.service;

import com.ksedu.labsystem.pojo.Right;

import java.util.List;

/**
 * @author Xzj
 * @Description
 * @create 2023/10/5 10:03
 */
public interface RightService {
    public List<Right> getRightList();

    void updateRightlist(Right right);

    void deleteRightList(Integer id);
}
