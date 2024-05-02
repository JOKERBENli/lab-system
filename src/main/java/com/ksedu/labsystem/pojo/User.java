package com.ksedu.labsystem.pojo;

import lombok.Data;

/**
 * @author Xzj
 * @Description
 * @create 2023/10/10 11:22
 */
@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private Integer roleId;
    private Integer is_default;
    private Role role;

    private String token;
}
