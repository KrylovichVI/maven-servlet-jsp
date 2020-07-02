package com.krylovichVI.service.impl;

import com.krylovichVI.dao.converters.BookConverter;
import com.krylovichVI.dao.entity.BookEntity;
import com.krylovichVI.dao.repository.BookRepo;
import com.krylovichVI.pojo.Book;
import com.krylovichVI.service.SearchService;
import com.krylovichVI.service.utils.ConvertImageUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class DefaultSearchService implements SearchService {
    private BookRepo bookRepo;
    private BookConverter bookConverter;

    public DefaultSearchService(BookRepo bookRepo, BookConverter bookConverter) {
        this.bookRepo = bookRepo;
        this.bookConverter = bookConverter;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Book> findBookBySearch(String filter, Pageable pageable) {
        Page<BookEntity> bookPages;
        if(filter != null && !filter.isEmpty()) {
            bookPages = bookRepo.findByBookName(filter, pageable);
        } else {
            bookPages = bookRepo.findAll(pageable);
        }

        return convertFilePathInBase64(bookConverter.toDto(bookPages));
    }

    private Page<Book> convertFilePathInBase64(Page<Book> books) {
        if(books == null){
            return null;
        }
        List<Book> bookList = ConvertImageUtils.convertFilePathInBase64(books.getContent());

        return new PageImpl<Book>(bookList, books.getPageable(), books.getTotalElements());
    }
}
