package com.krylovichVI.service.impl;

import com.krylovichVI.dao.BookDao;
import com.krylovichVI.dao.converters.BookConverter;
import com.krylovichVI.dao.entity.BookEntity;
import com.krylovichVI.pojo.Book;
import com.krylovichVI.pojo.Page;
import com.krylovichVI.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultBookService implements BookService {
    private final int MAX_ELEMENT_OF_PAGE = 5;
    private BookDao bookDao;
    private BookConverter bookConverter;

    @Autowired
    public DefaultBookService(BookDao bookDao, BookConverter bookConverter) {
        this.bookDao = bookDao;
        this.bookConverter = bookConverter;
    }

    @Transactional
    @Override
    public List<Book> getBooks() {
        return bookConverter.toDto(bookDao.getAllBooks());
    }

    @Transactional
    @Override
    public long addBook(Book book) {
        return bookDao.addBook(bookConverter.toEntity(book));
    }

    @Transactional
    @Override
    public void deleteBook(Long id) {
        BookEntity bookById = bookDao.getBookById(id);
        bookDao.deleteBook(bookById);
    }

    @Transactional
    @Override
    public long getCountOfPage() {
        long countOfRow = bookDao.getCountOfRow();
        if(countOfRow % MAX_ELEMENT_OF_PAGE != 0){
            return (int) Math.ceil((double) bookDao.getCountOfRow() / MAX_ELEMENT_OF_PAGE);
        } else {
            return countOfRow / MAX_ELEMENT_OF_PAGE;
        }
    }

    @Transactional
    @Override
    public List<Book> getBooksByPage(int currentPage) {
        List<Book> books = bookConverter.toDto(bookDao.getBooksByPage(new Page(currentPage, MAX_ELEMENT_OF_PAGE)));
        if(!books.isEmpty()){
            return books;
        } else {
            return null;
        }
    }

    @Transactional
    @Override
    public Book getBookById(Long bookId) {
        return bookConverter.toDto(bookDao.getBookById(bookId));
    }

    @Transactional
    @Override
    public List<Book> getListOfBookById(String[] bookId) {
        List<Book> bookList = new ArrayList<>();
        for(String str : bookId){
            bookList.add(bookConverter.toDto(bookDao.getBookById(Long.valueOf(str))));
        }
        return bookList;
    }
}
