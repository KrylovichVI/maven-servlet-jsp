package com.krylovichVI.dao;

import com.krylovichVI.dao.imp.DefaultBookDao;
import com.krylovichVI.pojo.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DefaultBookDaoTest {
    private BookDao bookDao;

    @BeforeEach
    void init(){
        bookDao = DefaultBookDao.getInstance();
    }

    @Test
    void testAddBook(){
        long id = bookDao.addBook(new Book("mybook", "my"));
        assertEquals(id, 3L);
    }

    @Test
    void testOfListBooks(){
        List<Book> booksBefore = bookDao.getBooks();
        bookDao.addBook(new Book("mybook1", "my"));
        List<Book> booksAfter = bookDao.getBooks();

        assertEquals(booksBefore.size() + 1, booksAfter.size());
    }



}
