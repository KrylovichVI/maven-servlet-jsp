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
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @Mock
    private BookDao bookDao;

    @InjectMocks
    private DefaultBookService bookService;

    @Test
    void testCorrectAddBook(){
        Book book = new Book("My book", "My author");
        when(bookDao.addBook(book)).thenReturn(anyLong());
        long id = bookService.addBook(book);
        assertNotNull(id);
    }

    @Test
    void testNullListOfBook(){
        when(bookDao.getBooksByPage()).thenReturn(null);
        List<Book> books = bookService.getBooks();
        assertNull(books);
    }

    @Test
    void testNotEmptyListOfBook(){
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book("book1", "book1"));
        bookList.add(new Book("book2", "book2"));
        bookList.add(new Book("book3", "book3"));
        when(bookDao.getBooksByPage()).thenReturn(bookList);

        List<Book> serviceBooks = bookService.getBooks();

        assertNotNull(serviceBooks);
        assertTrue(bookList.containsAll(serviceBooks));
    }

    @Test
    void testOfDeleteBook(){
        doNothing().when(bookDao).deleteBook(any());
        bookService.deleteBook(1L);
        verify(bookDao, times(1)).deleteBook(any());
    }

    @Test
    void testOfCountPage(){
        when(bookDao.getCountOfRow()).thenReturn(12);
        int countOfPage = bookService.getCountOfPage();
        assertEquals(countOfPage, 3);
    }

    @Test
    void testOfListBookInPage(){
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book("First", "First"));
        bookList.add(new Book("Second", "Second"));
        bookList.add(new Book("Thrid", "Thrid"));
        bookList.add(new Book("First", "First"));
        when(bookDao.getBooksByPage(5,1)).thenReturn(bookList);
        List<Book> booksByPage = bookService.getBooksByPage(1);

        assertTrue(bookList.containsAll(booksByPage));
    }

    @Test
    void testOfEmptyListOfBookInPage(){
        when(bookDao.getBooksByPage(5, 1)).thenReturn(Collections.emptyList());
        List<Book> booksByPage = bookService.getBooksByPage(1);

        assertNull(booksByPage);
    }
}
