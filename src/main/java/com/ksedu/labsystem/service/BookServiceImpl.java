package com.ksedu.labsystem.service;

import com.ksedu.labsystem.dao.BookMapper;
import com.ksedu.labsystem.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Xzj
 * @Description
 * @create 2024/2/25 10:57
 */
@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private BookMapper bookMapper;
    @Override
    public void addBookList(Book book) {
        bookMapper.addBookList(book);
    }

    @Override
    public List<Book> getBookList(Book book) {
        return bookMapper.getBookList(book);
    }

    @Override
    public void deleteBookList(Integer id) {
        bookMapper.deleteBookList(id);
    }

    @Override
    public void updateBookList(Book book) {
        bookMapper.updateBookList(book);
    }
}
