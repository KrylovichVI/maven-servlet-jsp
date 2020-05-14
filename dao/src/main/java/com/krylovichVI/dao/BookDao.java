package com.krylovichVI.dao;

import com.krylovichVI.pojo.Book;
import com.krylovichVI.pojo.Page;

import java.util.List;

public interface BookDao {
    List<Book> getAllBooks();

    long addBook(Book book);

    void deleteBook(Book book);

    Book getBookById(Long id);

    List<Book> getBooksByPage(Page page);

    long getCountOfRow();
}
