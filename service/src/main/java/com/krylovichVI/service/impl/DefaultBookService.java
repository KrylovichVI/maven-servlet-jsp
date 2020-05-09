package com.krylovichVI.service.impl;

import com.krylovichVI.dao.BookDao;
import com.krylovichVI.dao.imp.DefaultBookDao;
import com.krylovichVI.pojo.Book;
import com.krylovichVI.service.BookService;

import java.util.List;

public class DefaultBookService implements BookService {
    private final int COUNT_ELEMENT_OF_PAGE = 5;
    private static BookService instance;
    private BookDao bookDao;

    private DefaultBookService(){
        bookDao = DefaultBookDao.getInstance();
    }

    public static BookService getInstance(){
        synchronized (BookService.class) {
            if (instance == null) {
                instance = new DefaultBookService();
            }
            return instance;
        }
    }


    @Override
    public List<Book> getBooks() {
        return bookDao.getBooksByPage();
    }

    @Override
    public long addBook(Book book) {
        return bookDao.addBook(book);
    }

    @Override
    public void deleteBook(Long id) {
        Book bookById = bookDao.getBookById(id);
        bookDao.deleteBook(bookById);
    }

    @Override
    public int getCountOfPage() {
        int countOfRow = bookDao.getCountOfRow();
        if(countOfRow % 5 != 0){
            return (int) Math.ceil((double) bookDao.getCountOfRow() / 5);
        } else {
            return countOfRow / 5;
        }
    }

    @Override
    public List<Book> getBooksByPage(int page) {
        List<Book> books = bookDao.getBooksByPage(COUNT_ELEMENT_OF_PAGE, page);
        if(!books.isEmpty()){
            return books;
        } else {
            return null;
        }
    }
}
