//package com.krylovichVI.dao;
//
//import com.krylovichVI.dao.imp.DefaultBookDao;
//import com.krylovichVI.pojo.Book;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.AfterAll;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class DefaultBookDaoTest {
//    private BookDao bookDao;
//
//    @BeforeEach
//    void init(){
//        bookDao = DefaultBookDao.getInstance();
//    }
//
//    @Test
//    void testAddBook(){
//        Book book = new Book("mybook", "my");
//        long id = bookDao.addBook(book);
//
//        assertEquals(bookDao.getBookById(id), book);
//
//        bookDao.deleteBook(book);
//    }
//
//    @Test
//
//    void testOfDeleteBook(){
//        Book book = new Book("Book", "Author");
//        long id = bookDao.addBook(book);
//        bookDao.deleteBook(book);
//
//        assertThrows(NullPointerException.class, () -> bookDao.getBookById(id));
//
//    }
//    @Test
//    void testOfListBooks(){
//        Book bookFirst = new Book("Book1", "Author");
//        Book bookSecond = new Book("Book2", "Author");
//        bookDao.addBook(bookFirst);
//        bookDao.addBook(bookSecond);
//        List<Book> bookList = bookDao.getBooksByPage();
//
//        assertTrue(bookList.contains(bookFirst));
//        assertTrue(bookList.contains(bookSecond));
//
//        bookDao.deleteBook(bookFirst);
//        bookDao.deleteBook(bookSecond);
//    }
//
//    @Test
//    void testOfGetBookById(){
//        Book book = new Book("Head First", "Author");
//        long id = bookDao.addBook(book);
//
//        assertNotNull(bookDao.getBookById(id));
//
//        bookDao.deleteBook(book);
//    }
//
//    @Test
//    void testOfCountRow(){
//        Book bookFirst = new Book("Book1", "Author");
//        Book bookSecond = new Book("Book2", "Author");
//        Book bookThird = new Book("Book3", "Author");
//
//        bookDao.addBook(bookFirst);
//        bookDao.addBook(bookSecond);
//        bookDao.addBook(bookThird);
//
//        int countOfRow = bookDao.getCountOfRow();
//
//        assertEquals(countOfRow, 3);
//
//        bookDao.deleteBook(bookFirst);
//        bookDao.deleteBook(bookSecond);
//        bookDao.deleteBook(bookThird);
//    }
//
//    @Test
//    void testOfFirstPage(){
//        Book bookFirst = new Book("Book1", "Author");
//        Book bookSecond = new Book("Book2", "Author");
//        Book bookThird = new Book("Book3", "Author");
//        Book bookForth = new Book("Book4", "Author");
//        Book bookFifth = new Book("Book5", "Author");
//        Book bookSix = new Book("Book6", "Author");
//
//        bookDao.addBook(bookFirst);
//        bookDao.addBook(bookSecond);
//        bookDao.addBook(bookThird);
//        bookDao.addBook(bookForth);
//        bookDao.addBook(bookFifth);
//        bookDao.addBook(bookSix);
//
//        List<Book> booksByPage = bookDao.getBooksByPage(5, 1);
//
//        assertEquals(booksByPage.size(), 5);
//
//        bookDao.deleteBook(bookFirst);
//        bookDao.deleteBook(bookSecond);
//        bookDao.deleteBook(bookThird);
//        bookDao.deleteBook(bookForth);
//        bookDao.deleteBook(bookFifth);
//        bookDao.deleteBook(bookSix);
//    }
//
//@AfterAll
//    void closeSession(){
//            SessionUtil.closeSessionFactory();
//            }
//}
