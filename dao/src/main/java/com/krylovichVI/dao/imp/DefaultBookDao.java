package com.krylovichVI.dao.imp;

import com.krylovichVI.dao.BookDao;
import com.krylovichVI.pojo.Book;
import com.krylovichVI.pojo.Page;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.NoResultException;
import java.util.List;

public class DefaultBookDao extends DefaultPageDao<Book> implements BookDao {
    private static final Logger logger = LoggerFactory.getLogger(DefaultBookDao.class);

    private final SessionFactory factory;

    public DefaultBookDao(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> listOfBooks = factory.getCurrentSession().createQuery("select b from Book b order by b.id desc")
                .getResultList();
        return listOfBooks;
    }

    @Override
    public long addBook(Book book) {
        try{
            Long id = (Long) factory.getCurrentSession().save(book);
            logger.info("book {} add ", book.getAuthor(), book.getBookName());
            return id;
        } catch (NoResultException e) {
            logger.error("book {} error add ", book.getAuthor(), book.getBookName(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteBook(Book book) {
        Session session = factory.getCurrentSession();
        session.delete(book);
        session.flush();
        logger.info("book {} delete ", book.getAuthor(), book.getBookName());
    }

    @Override
    public Book getBookById(Long id) {
        try{
            Book book = factory.getCurrentSession().get(Book.class, id);
            logger.info("book {} get book by id ", book.getAuthor(), book.getBookName());
            return book;
        } catch(NoResultException e){
            logger.error("book {} error get book by id ", id);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Book> getBooksByPage(Page page) {
        return super.listOfPage(Book.class, factory, page);
    }

    @Override
    public long getCountOfRow() {
        return super.countOfRow(Book.class, factory);
    }
}
