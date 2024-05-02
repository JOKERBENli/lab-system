package com.ksedu.labsystem.service;

import com.ksedu.labsystem.pojo.Lab;

import java.util.List;

/**
 * @author Xzj
 * @Description
 * @create 2023/10/11 15:08
 */
public interface LabService {
    public List<Lab> getLabList(Lab lab);
    public void addLabList(Lab lab);

    void updateLabList(Lab lab);

    void deleteLabList(Integer id);
}
