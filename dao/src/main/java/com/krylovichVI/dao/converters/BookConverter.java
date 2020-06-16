package com.krylovichVI.dao.converters;

import com.krylovichVI.dao.entity.BookEntity;
import com.krylovichVI.dao.entity.OrderEntity;
import com.krylovichVI.dao.repository.OrderRepo;
import com.krylovichVI.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookConverter implements AbstractConverter<BookEntity, Book> {
    @Autowired
    private OrderRepo orderRepo;

    @Override
    public Book toDto(BookEntity bookEntity){
        if(bookEntity == null){
            return null;
        }

        Book book = new Book();

        book.setOrderIdList( toOrderIdFromOrderEntity( bookEntity.getOrderList() ) );
        book.setId(bookEntity.getId());
        book.setBookName(bookEntity.getBookName());
        book.setAuthor(bookEntity.getAuthor());

        return book;
    }

    private List<Long> toOrderIdFromOrderEntity(List<OrderEntity> orderList){
        if(orderList == null){
            return null;
        }
        List<Long> list = new ArrayList<>(orderList.size());

        orderList.forEach(order ->{
            list.add(order.getId());
        });

        return list;
    }

    @Override
    public BookEntity toEntity(Book book){
        if(book == null){
            return null;
        }

        BookEntity bookEntity = new BookEntity();

        bookEntity.setOrderList(orderRepo.findAllById(book.getOrderIdList()));
        bookEntity.setId(book.getId());
        bookEntity.setBookName(book.getBookName());
        bookEntity.setAuthor(book.getAuthor());

        return bookEntity;
    }

    public List<BookEntity> toEntity(List<Book> books){
        if(books == null){
            return null;
        }

        List<BookEntity> list = new ArrayList<>(books.size());
        for(Book book : books){
            list.add(toEntity(book));
        }

        return list;
    }

    public List<Book> toDto(List<BookEntity> bookEntities){
        if(bookEntities == null){
            return null;
        }

        List<Book> list = new ArrayList<>(bookEntities.size());
        for(BookEntity bookEntity : bookEntities){
            list.add(toDto(bookEntity ));
        }

        return list;
    }
}