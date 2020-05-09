package com.krylovichVI.dao.imp;

import com.krylovichVI.dao.BookDao;
import com.krylovichVI.dao.utils.SessionUtil;
import com.krylovichVI.pojo.Book;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.util.List;

public class DefaultBookDao implements BookDao {
    private static final Logger logger = LoggerFactory.getLogger(DefaultBookDao.class);
    private static BookDao instance;

    private DefaultBookDao(){ }

    public static BookDao getInstance(){
        if(instance == null){
            instance = new DefaultBookDao();
        }
        return instance;
    }

    @Override
    public List<Book> getBooksByPage() {
        Transaction transaction = null;
        try(Session session = SessionUtil.openSession()){
            transaction = session.getTransaction();
            transaction.begin();
            List<Book> listOfBooks = session.createQuery("select b from Book b order by b.id desc").getResultList();
            transaction.commit();
            session.close();
            return listOfBooks;
        }
    }

    @Override
    public long addBook(Book book) {
        Transaction transaction = null;
        Long id;
        try(Session session = SessionUtil.openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            id = (Long) session.save(book);
            transaction.commit();
            logger.info("book {} add ", book.getAuthor(), book.getBookName());
            return id;
        } catch (HibernateException e) {
            transaction.rollback();
            logger.error("book {} error add ", book.getAuthor(), book.getBookName(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteBook(Book book) {
        Transaction transaction = null;
        try(Session session = SessionUtil.openSession()){
            transaction = session.getTransaction();
            transaction.begin();
            session.delete(book);
            transaction.commit();
            logger.info("book {} delete ", book.getAuthor(), book.getBookName());
        } catch (HibernateException e) {
            transaction.rollback();
            logger.error("book {} error delete ", book.getAuthor(), book.getBookName(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Book getBookById(Long id) {
        try(Session session = SessionUtil.openSession()){
            Transaction transaction = session.getTransaction();
            transaction.begin();
            Book book = session.get(Book.class, id);
            transaction.commit();
            logger.info("book {} get book by id ", book.getAuthor(), book.getBookName());
            return book;
        }
    }

    @Override
    public List<Book> getBooksByPage(int countElement, int page) {
        try(Session session = SessionUtil.openSession()){
            Transaction transaction = session.getTransaction();
            transaction.begin();
            List<Book> list = session.createQuery("select b from Book b order by b.id desc")
                    .setFirstResult(countElement * (page - 1))
                    .setMaxResults(countElement)
                    .getResultList();
            transaction.commit();
            return list;
        }
    }

    @Override
    public int getCountOfRow() {
        String sql = "select count(*) from books";
        try(Session session = SessionUtil.openSession()){
            Transaction transaction = session.getTransaction();
            transaction.begin();
            BigInteger count = (BigInteger)session.createNativeQuery(sql).getSingleResult();
            transaction.commit();
            logger.info("book {} get count of books ", count);
            return count.intValue();
        }
    }
}
