//package com.krylovichVI.service;
//
//import com.krylovichVI.dao.BookDao;
//import com.krylovichVI.pojo.Book;
//import com.krylovichVI.service.impl.DefaultBookService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.Mockito.*;
//
//
//@ExtendWith(MockitoExtension.class)
//public class BookServiceTest {
//    @Mock
//    private BookDao bookDao;
//
//    @InjectMocks
//    private DefaultBookService bookService;
//
//    @Test
//    void testCorrectAddBook(){
//        Book book = new Book("My book", "My author");
//        when(bookDao.addBook(book)).thenReturn(anyLong());
//        long id = bookService.addBook(book);
//        assertNotNull(id);
//    }
//
//    @Test
//    void testNullListOfBook(){
//        when(bookDao.getAllBooks()).thenReturn(null);
//        List<Book> books = bookService.getBooks();
//        assertNull(books);
//    }
//
//    @Test
//    void testNotEmptyListOfBook(){
//        List<Book> bookList = new ArrayList<>();
//        bookList.add(new Book("book1", "book1"));
//        bookList.add(new Book("book2", "book2"));
//        bookList.add(new Book("book3", "book3"));
//        when(bookDao.getAllBooks()).thenReturn(bookList);
//
//        List<Book> serviceBooks = bookService.getBooks();
//
//        assertNotNull(serviceBooks);
//        assertTrue(bookList.containsAll(serviceBooks));
//    }
//
//    @Test
//    void testOfDeleteBook(){
//        doNothing().when(bookDao).deleteBook(any());
//        bookService.deleteBook(1L);
//        verify(bookDao, times(1)).deleteBook(any());
//    }
//
//    @Test
//    void testOfCountPage(){
//        when(bookDao.getCountOfRow()).thenReturn(12L);
//        long countOfPage = bookService.getCountOfPage();
//        assertEquals(countOfPage, 3L);
//    }
//
//    @Test
//    void testOfGetBookById(){
//        Book resultBook = new Book("MyBook", "Test");
//        when(bookDao.getBookById(1L)).thenReturn(resultBook);
//        Book bookById = bookService.getBookById(1L);
//        assertEquals(bookById, resultBook);
//    }
//}
