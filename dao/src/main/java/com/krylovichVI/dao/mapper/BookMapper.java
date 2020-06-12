package com.krylovichVI.dao.mapper;

import com.krylovichVI.dao.entity.BookEntity;
import com.krylovichVI.pojo.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {OrderMapper.class})
public interface BookMapper extends AbstractMapper<BookEntity, Book> {

    @Mapping(target = "orderList", ignore = true)
    @Override
    Book toDto(BookEntity bookEntity);

    @Override
    BookEntity toEntity(Book book);

    List<BookEntity> toEntity(List<Book> books);

    List<Book> toDto(List<BookEntity> bookEntities);

    default BookEntity fromId(Long id){
        if(id == null){
            return null;
        }
        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(id);
        return bookEntity;
    }
}
