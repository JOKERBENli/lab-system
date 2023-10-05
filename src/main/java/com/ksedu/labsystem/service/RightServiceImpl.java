package com.ksedu.labsystem.service;

import com.ksedu.labsystem.dao.RightMapper;
import com.ksedu.labsystem.pojo.Right;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Xzj
 * @Description
 * @create 2023/10/5 10:04
 */
@Service
public class RightServiceImpl implements RightService{
    @Autowired
    private RightMapper rightMapper;

    @Override
    public List<Right> getRightList() {
        return rightMapper.getRightList();
    }
}
