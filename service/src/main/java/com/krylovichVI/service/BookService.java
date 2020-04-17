package com.krylovichVI.service;

import com.krylovichVI.pojo.Book;

import java.util.List;

public interface BookService {
    List<Book> getBooks();

    long addBook(Book user);
}
