package com.krylovichVI.dao.repository;

import com.krylovichVI.dao.entity.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<BookEntity, Long> {
  Page<BookEntity> findByBookName(String bookName, Pageable pageable);
}
