package com.krylovichVI.service.impl;

import com.krylovichVI.dao.BookDao;
import com.krylovichVI.dao.imp.DefaultBookDao;
import com.krylovichVI.pojo.Book;
import com.krylovichVI.service.BookService;

import java.util.List;

public class DefaultBookService implements BookService {
    private static BookService instance;
    private BookDao authUserDao;

    private DefaultBookService(){
        authUserDao = DefaultBookDao.getInstance();
    }

    public static BookService getInstance(){
        synchronized (BookService.class) {
            if (instance == null) {
                instance = new DefaultBookService();
            }
            return instance;
        }
    }


    @Override
    public List<Book> getBooks() {
        return authUserDao.getBooks();
    }

    @Override
    public long addBook(Book book) {
        return authUserDao.addBook(book);
    }
}
