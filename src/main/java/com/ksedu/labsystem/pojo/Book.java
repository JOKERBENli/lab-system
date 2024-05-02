package com.ksedu.labsystem.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author Xzj
 * @Description
 * @create 2024/2/24 16:10
 */
@Data
public class Book {
    private Integer id;
    private Integer lab_id;
    private Date book_time;
    private Integer book_class;
    private String book_username;
    private String book_reason;
    private Integer book_state;
    private Lab lab;



}
