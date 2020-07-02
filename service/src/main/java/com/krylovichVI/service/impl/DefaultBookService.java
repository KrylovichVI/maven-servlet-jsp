package com.krylovichVI.service.impl;

import com.krylovichVI.dao.BookDao;
import com.krylovichVI.dao.converters.BookConverter;
import com.krylovichVI.dao.entity.BookEntity;
import com.krylovichVI.pojo.Book;
import com.krylovichVI.pojo.Page;
import com.krylovichVI.service.BookService;
import com.krylovichVI.service.utils.ConvertImageUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public class DefaultBookService implements BookService {
    private final int MAX_ELEMENT_OF_PAGE = 5;
    private BookDao bookDao;
    private BookConverter bookConverter;

    public DefaultBookService(BookDao bookDao, BookConverter bookConverter) {
        this.bookDao = bookDao;
        this.bookConverter = bookConverter;
    }

    @Transactional(readOnly = true)
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

    @Transactional(readOnly = true)
    @Override
    public long getCountOfPage() {
        long countOfRow = bookDao.getCountOfRow();
        if(countOfRow % MAX_ELEMENT_OF_PAGE != 0){
            return (int) Math.ceil((double) bookDao.getCountOfRow() / MAX_ELEMENT_OF_PAGE);
        } else {
            return countOfRow / MAX_ELEMENT_OF_PAGE;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> getBooksByPage(int currentPage) {
        List<BookEntity> booksByPage = bookDao.getBooksByPage(new Page(currentPage, MAX_ELEMENT_OF_PAGE));

        List<Book> books = bookConverter.toDto(booksByPage);

        List<Book> bookList = convertFilePathInBase64(books);
        if(!bookList.isEmpty()){
            return bookList;
        } else {
            return null;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Book getBookById(Long bookId) {
        return bookConverter.toDto(bookDao.getBookById(bookId));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> getListOfBookById(String[] bookId) {
        List<Book> bookList = new ArrayList<>();
        for(String str : bookId){
            BookEntity bookById = bookDao.getBookById(Long.valueOf(str));
            bookList.add(bookConverter.toDto(bookById));
        }
        return bookList;
    }


    private List<Book> convertFilePathInBase64(List<Book> books) {
        if(books == null){
            return null;
        }

        return ConvertImageUtils.convertFilePathInBase64(books);
    }
}
