package com.krylovichVI.dao.converter;

import com.krylovichVI.dao.config.HibernateConfig;
import com.krylovichVI.dao.converters.BookConverter;
import com.krylovichVI.dao.entity.BookEntity;
import com.krylovichVI.dao.entity.OrderEntity;
import com.krylovichVI.pojo.Book;
import com.krylovichVI.pojo.Order;
import com.krylovichVI.pojo.Status;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateConfig.class})
public class BookMapperTest {
    @Autowired
    private BookConverter bookConverter;

    @Test
    void testToDto(){
        List<OrderEntity> orderEntityList = new ArrayList<>();
        BookEntity bookEntity = new BookEntity("MyBook", "KrylovichVI");
        OrderEntity orderEntity = new OrderEntity(null, Date.valueOf(LocalDate.now()), Status.CONFIRMED, "order");
        orderEntity.setId(1L);
        orderEntityList.add(orderEntity);
        bookEntity.setOrderList(orderEntityList);
        Book bookDto = bookConverter.toDto(bookEntity);


        assertEquals(bookDto.getAuthor(),  bookEntity.getAuthor());
        assertEquals(bookDto.getBookName(),  bookEntity.getBookName());
    }

    @Test
    void testToEntity(){
        List<Long> orderList = new ArrayList<>();
        Book book = new Book("MyBook", "KrylovichVI");
        Order order = new Order(null, Date.valueOf(LocalDate.now()), Status.CONFIRMED, "order");
        order.setId(1L);
        orderList.add(order.getId());
        book.setOrderIdList(orderList);

        BookEntity bookEntity = bookConverter.toEntity(book);

        assertEquals(bookEntity.getAuthor(),  book.getAuthor());
        assertEquals(bookEntity.getBookName(),  book.getBookName());
        assertTrue(bookEntity.getOrderList().isEmpty());
    }
}
