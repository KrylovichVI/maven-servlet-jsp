package com.krylovichVI.dao;

import com.krylovichVI.pojo.Book;
import java.util.List;

public interface BookDao {
    List<Book> getBooks();

    long addBook(Book user);
}
