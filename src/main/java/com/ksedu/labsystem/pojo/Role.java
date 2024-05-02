package com.ksedu.labsystem.pojo;

import lombok.Data;

/**
 * @author Xzj
 * @Description
 * @create 2023/10/9 13:21
 */
@Data
public class Role {
    private Integer id;
    private String roleName;
    private Integer roleType;
    private String rights;

}
