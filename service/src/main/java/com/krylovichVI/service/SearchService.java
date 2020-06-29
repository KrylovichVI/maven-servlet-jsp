package com.krylovichVI.service;

import com.krylovichVI.pojo.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchService {
    Page<Book> findBookBySearch(String bookName, Pageable pageable);
}
