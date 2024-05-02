package com.ksedu.labsystem.service;

import com.ksedu.labsystem.pojo.Book;

import java.util.List;

/**
 * @author Xzj
 * @Description
 * @create 2024/2/24 22:14
 */
public interface BookService {
    public void addBookList(Book book);

    List<Book> getBookList(Book book);

    void deleteBookList(Integer id);

    void updateBookList(Book book);
}
