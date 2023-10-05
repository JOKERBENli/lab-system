package com.ksedu.labsystem;

import com.ksedu.labsystem.dao.RightMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LabsystemApplicationTests {
    @Autowired
    private RightMapper rightMapper;
    @Test
    void contextLoads() {
        System.out.println(rightMapper.getRightList());
    }

}
