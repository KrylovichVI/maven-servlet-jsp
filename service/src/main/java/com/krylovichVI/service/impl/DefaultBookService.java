package com.krylovichVI.service.impl;

import com.krylovichVI.dao.BookDao;
import com.krylovichVI.dao.imp.DefaultBookDao;
import com.krylovichVI.pojo.Book;
import com.krylovichVI.pojo.Page;
import com.krylovichVI.service.BookService;

import java.util.ArrayList;
import java.util.List;

public class DefaultBookService implements BookService {
    private final int MAX_ELEMENT_OF_PAGE = 5;
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
        return bookDao.getAllBooks();
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
    public long getCountOfPage() {
        long countOfRow = bookDao.getCountOfRow();
        if(countOfRow % MAX_ELEMENT_OF_PAGE != 0){
            return (int) Math.ceil((double) bookDao.getCountOfRow() / MAX_ELEMENT_OF_PAGE);
        } else {
            return countOfRow / MAX_ELEMENT_OF_PAGE;
        }
    }

    @Override
    public List<Book> getBooksByPage(int currentPage) {
        List<Book> books = bookDao.getBooksByPage(new Page(currentPage, MAX_ELEMENT_OF_PAGE));
        if(!books.isEmpty()){
            return books;
        } else {
            return null;
        }
    }

    @Override
    public Book getBookById(Long bookId) {
        return bookDao.getBookById(bookId);
    }

    @Override
    public List<Book> getListOfBookById(String[] bookId) {
        List<Book> bookList = new ArrayList<>();
        for(String str : bookId){
            bookList.add(bookDao.getBookById(Long.valueOf(str)));
        }
        return bookList;
    }
}
