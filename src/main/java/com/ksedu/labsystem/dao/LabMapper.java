package com.ksedu.labsystem.dao;

import com.ksedu.labsystem.pojo.Lab;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Xzj
 * @Description
 * @create 2023/10/11 14:27
 */
@Mapper
public interface LabMapper {
    public List<Lab> getLabList(Lab lab);
    public void addLabList(Lab lab);

    void updateLabList(Lab lab);

    void deleteLabList(Integer id);
}
