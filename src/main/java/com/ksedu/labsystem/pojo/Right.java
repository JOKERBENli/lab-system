package com.ksedu.labsystem.pojo;


import lombok.Data;

import java.util.List;

/**
 * @author Xzj
 * @Description
 * @create 2023/10/1 20:21
 */

@Data

public class Right {
    private Integer id;
    private String title;
    private String icon;
    private String path;
    private List<Right> children;






}
