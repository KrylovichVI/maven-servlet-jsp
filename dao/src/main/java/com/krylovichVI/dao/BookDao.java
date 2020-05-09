package com.krylovichVI.dao;

import com.krylovichVI.pojo.Book;
import java.util.List;

public interface BookDao {
    List<Book> getBooksByPage();

    long addBook(Book book);

    void deleteBook(Book book);

    Book getBookById(Long id);

    List<Book> getBooksByPage(int countElement, int page);

    int getCountOfRow();
}
