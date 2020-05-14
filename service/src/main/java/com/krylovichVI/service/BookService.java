package com.krylovichVI.service;

import com.krylovichVI.pojo.Book;

import java.util.List;

public interface BookService {
    List<Book> getBooks();

    long addBook(Book book);

    void deleteBook(Long id);

    long getCountOfPage();

    List<Book> getBooksByPage(int page);

    Book getBookById(Long bookId);

    List<Book> getListOfBookById(String[] bookId);
}
