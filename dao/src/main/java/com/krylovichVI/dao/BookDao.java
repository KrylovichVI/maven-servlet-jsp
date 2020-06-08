package com.krylovichVI.dao;

import com.krylovichVI.dao.entity.BookEntity;
import com.krylovichVI.pojo.Page;

import java.util.List;

public interface BookDao {
    List<BookEntity> getAllBooks();

    long addBook(BookEntity book);

    void deleteBook(BookEntity book);

    BookEntity getBookById(Long id);

    List<BookEntity> getBooksByPage(Page page);

    long getCountOfRow();
}
