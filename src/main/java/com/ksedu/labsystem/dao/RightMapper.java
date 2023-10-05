package com.ksedu.labsystem.dao;

import com.ksedu.labsystem.pojo.Right;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Xzj
 * @Description
 * @create 2023/10/1 20:42
 */
@Mapper
public interface RightMapper {
//    @Select("select * from rights")
    public List<Right> getRightList();
}
