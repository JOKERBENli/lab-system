package com.ksedu.labsystem.dao;

import com.ksedu.labsystem.pojo.Book;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Xzj
 * @Description
 * @create 2023/10/11 14:27
 */
@Mapper
public interface BookMapper {
    public void addBookList(Book book);

    List<Book> getBookList(Book book);

    void deleteBookList(Integer id);

    void updateBookList(Book book);
}
