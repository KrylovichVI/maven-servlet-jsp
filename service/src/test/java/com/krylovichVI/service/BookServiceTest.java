package com.krylovichVI.service;

import com.krylovichVI.dao.BookDao;
import com.krylovichVI.dao.converters.BookConverter;
import com.krylovichVI.dao.entity.BookEntity;
import com.krylovichVI.pojo.Book;
import com.krylovichVI.service.impl.DefaultBookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@Transactional
public class BookServiceTest {
    @Mock
    private BookDao bookDao;
    @Mock
    private BookConverter bookConverter;

    @InjectMocks
    private DefaultBookService bookService;

    @Test
    void testCorrectAddBook(){
        Book book = new Book("My book", "My author");
        BookEntity bookEntity = new BookEntity("My book", "My author");

        when(bookConverter.toEntity(book)).thenReturn(bookEntity);
        when(bookDao.addBook(bookEntity)).thenReturn(anyLong());
        long id = bookService.addBook(book);
        assertNotNull(id);
    }

    @Test
    void testNullListOfBook(){
        List<BookEntity> bookEntities = null;
        List<Book> bookList = null;

        when(bookDao.getAllBooks()).thenReturn(bookEntities);
        when(bookConverter.toDto(bookEntities)).thenReturn(bookList);

        List<Book> books = bookService.getBooks();
        assertNull(books);
    }

    @Test
    void testNotEmptyListOfBook(){
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book("book1", "book1"));
        bookList.add(new Book("book2", "book2"));
        bookList.add(new Book("book3", "book3"));

        List<BookEntity> bookEntityList = new ArrayList<>();
        bookEntityList.add(new BookEntity("book1", "book1"));
        bookEntityList.add(new BookEntity("book2", "book2"));
        bookEntityList.add(new BookEntity("book3", "book3"));

        when(bookDao.getAllBooks()).thenReturn(bookEntityList);
        when(bookConverter.toDto(bookEntityList)).thenReturn(bookList);

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
        when(bookDao.getCountOfRow()).thenReturn(12L);
        long countOfPage = bookService.getCountOfPage();
        assertEquals(countOfPage, 3L);
    }

    @Test
    void testOfGetBookById(){
        Book resultBook = new Book("MyBook", "Test");
        BookEntity resultBookEntity = new BookEntity("MyBook", "Test");

        when(bookDao.getBookById(1L)).thenReturn(resultBookEntity);
        when(bookConverter.toDto(resultBookEntity)).thenReturn(resultBook);

        Book bookById = bookService.getBookById(1L);
        assertEquals(bookById, resultBook);
    }
}
