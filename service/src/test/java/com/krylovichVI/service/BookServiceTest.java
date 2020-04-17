package com.krylovichVI.service;

import com.krylovichVI.dao.BookDao;
import com.krylovichVI.pojo.Book;
import com.krylovichVI.service.impl.DefaultBookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @Mock
    private BookDao bookDao;

    @InjectMocks
    private DefaultBookService bookService;

    @Test
    void testCorrectAddBook(){
        Book book = new Book(1L,"My book", "My author");
        when(bookDao.addBook(book)).thenReturn(book.getId());
        long id = bookService.addBook(book);
        assertEquals(book.getId(), id);
    }

    @Test
    void testNullListOfBook(){
        when(bookDao.getBooks()).thenReturn(null);
        List<Book> books = bookService.getBooks();
        assertNull(books);
    }

    @Test
    void testNotEmptyListOfBook(){
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book("book1", "book1"));
        bookList.add(new Book("book2", "book2"));
        bookList.add(new Book("book3", "book3"));
        when(bookDao.getBooks()).thenReturn(bookList);

        List<Book> serviceBooks = bookService.getBooks();

        assertNotNull(serviceBooks);
        assertTrue(bookList.containsAll(serviceBooks));
    }
}
