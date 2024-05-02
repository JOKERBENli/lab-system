package com.ksedu.labsystem.controller;

import com.ksedu.labsystem.pojo.Book;


import com.ksedu.labsystem.service.BookService;
import com.ksedu.labsystem.utils.ResultOBJ;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Xzj
 * @Description
 * @create 2023/10/11 15:13
 */
@RestController
@RequestMapping("/adminapi/books")
public class BookController {
    @Autowired
    private BookService bookService;
    private static final Logger logger = LogManager.getLogger(UserController.class);



    @GetMapping
    public List<Book> getBookList(Book book){
        return bookService.getBookList(book);
    }

    @PostMapping
    public ResultOBJ addBookList(@RequestBody Book book){
        try{
            bookService.addBookList(book);
            return ResultOBJ.ADD_SUCCESS;
        }catch (Exception e){
            return ResultOBJ.ADD_ERROR;
        }
    }
    @PostMapping("/select")
    public List<Book> getSelectBookList(@RequestBody Book book){

        return bookService.getBookList(book);
    }
    @PutMapping(value = "/{id}")
    public ResultOBJ updateBookList(@PathVariable Integer id,@RequestBody Book book){

        try{
            book.setId(id);
            bookService.updateBookList(book);
            logger.info("updateBookList test-------");
            return ResultOBJ.UPDATE_SUCCESS;
        }catch (Exception e){
            return ResultOBJ.UPDATE_ERROR;
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResultOBJ deleteBookList(@PathVariable Integer id){
        try{
            bookService.deleteBookList(id);
            return ResultOBJ.DELETE_SUCCESS;
        }catch (Exception e){
            return ResultOBJ.DELETE_ERROR;
        }
    }

}
