package com.krylovichVI.service.impl;

import com.krylovichVI.dao.converters.BookConverter;
import com.krylovichVI.dao.entity.BookEntity;
import com.krylovichVI.dao.repository.BookRepo;
import com.krylovichVI.pojo.Book;
import com.krylovichVI.service.SearchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

public class DefaultSearchService implements SearchService {
    private BookRepo bookRepo;
    private BookConverter bookConverter;

    public DefaultSearchService(BookRepo bookRepo, BookConverter bookConverter) {
        this.bookRepo = bookRepo;
        this.bookConverter = bookConverter;
    }

    @Transactional
    @Override
    public Page<Book> findBookBySearch(String filter, Pageable pageable) {
        Page<BookEntity> bookPages;
        if(filter != null && !filter.isEmpty()) {
            bookPages = bookRepo.findByBookName(filter, pageable);
        } else {
            bookPages = bookRepo.findAll(pageable);
        }

        return bookConverter.toDto(bookPages);
    }
}
