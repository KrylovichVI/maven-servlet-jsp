package com.krylovichVI.dao;

import com.krylovichVI.dao.config.DaoConfig;
import com.krylovichVI.dao.entity.BookEntity;
import com.krylovichVI.pojo.Page;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DaoConfig.class})
@Transactional
public class DefaultBookDaoTest {
    @Autowired
    private BookDao bookDao;

    @Test
    void testAddBook(){
        BookEntity book = new BookEntity("mybook", "my");
        long id = bookDao.addBook(book);

        assertEquals(bookDao.getBookById(id), book);

        bookDao.deleteBook(book);
    }

    @Test

    void testOfDeleteBook(){
        BookEntity book = new BookEntity("Book", "Author");
        long id = bookDao.addBook(book);
        bookDao.deleteBook(book);

        assertThrows(NullPointerException.class, () -> bookDao.getBookById(id));

    }
    @Test
    void testOfListBooks(){
        BookEntity bookFirst = new BookEntity("Book1", "Author");
        BookEntity bookSecond = new BookEntity("Book2", "Author");
        bookDao.addBook(bookFirst);
        bookDao.addBook(bookSecond);
        List<BookEntity> bookList = bookDao.getAllBooks();

        assertTrue(bookList.contains(bookFirst));
        assertTrue(bookList.contains(bookSecond));

        bookDao.deleteBook(bookFirst);
        bookDao.deleteBook(bookSecond);
    }

    @Test
    void testOfGetBookById(){
        BookEntity book = new BookEntity("Head First", "Author");
        long id = bookDao.addBook(book);

        assertNotNull(bookDao.getBookById(id));

        bookDao.deleteBook(book);
    }

    @Test
    void testOfCountRow(){
        BookEntity bookFirst = new BookEntity("Book1", "Author");
        BookEntity bookSecond = new BookEntity("Book2", "Author");
        BookEntity bookThird = new BookEntity("Book3", "Author");

        bookDao.addBook(bookFirst);
        bookDao.addBook(bookSecond);
        bookDao.addBook(bookThird);

        long countOfRow = bookDao.getCountOfRow();

        assertEquals(countOfRow, 3L);

        bookDao.deleteBook(bookFirst);
        bookDao.deleteBook(bookSecond);
        bookDao.deleteBook(bookThird);
    }

    @Test
    void testOfFirstPage(){
        BookEntity bookFirst = new BookEntity("Book1", "Author");
        BookEntity bookSecond = new BookEntity("Book2", "Author");
        BookEntity bookThird = new BookEntity("Book3", "Author");
        BookEntity bookForth = new BookEntity("Book4", "Author");
        BookEntity bookFifth = new BookEntity("Book5", "Author");
        BookEntity bookSix = new BookEntity("Book6", "Author");

        bookDao.addBook(bookFirst);
        bookDao.addBook(bookSecond);
        bookDao.addBook(bookThird);
        bookDao.addBook(bookForth);
        bookDao.addBook(bookFifth);
        bookDao.addBook(bookSix);

        List<BookEntity> booksByPage = bookDao.getBooksByPage(new Page(1, 1));

        assertEquals(booksByPage.size(), 1);

        bookDao.deleteBook(bookFirst);
        bookDao.deleteBook(bookSecond);
        bookDao.deleteBook(bookThird);
        bookDao.deleteBook(bookForth);
        bookDao.deleteBook(bookFifth);
        bookDao.deleteBook(bookSix);
    }
}
