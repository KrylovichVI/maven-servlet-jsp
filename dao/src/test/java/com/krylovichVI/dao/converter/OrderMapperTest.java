package com.krylovichVI.dao.converter;

import com.krylovichVI.dao.config.HibernateConfig;
import com.krylovichVI.dao.converters.OrderConverter;
import com.krylovichVI.dao.entity.AuthUserEntity;
import com.krylovichVI.dao.entity.BookEntity;
import com.krylovichVI.dao.entity.OrderEntity;
import com.krylovichVI.pojo.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateConfig.class})
public class OrderMapperTest {
    @Autowired
    private OrderConverter orderConverter;

    @Test
    void testToDto(){
        Set<BookEntity> bookEntities = new HashSet<>();
        bookEntities.add(new BookEntity("MyFirstBook", "KrylovichVI"));
        bookEntities.add(new BookEntity("MySecondBook", "KrylovichVI"));
        AuthUserEntity authUser = new AuthUserEntity("myTest", "123456", Role.USER, null);
        authUser.setId(1L);
        OrderEntity orderEntity = new OrderEntity(authUser, Date.valueOf(LocalDate.now()), Status.CONFIRMED, "order");
        orderEntity.setId(1L);
        orderEntity.setBookSet(bookEntities);

        Order orderDto =  orderConverter.toDto(orderEntity);

        orderDto.getBookSet().forEach(
                order -> {
                    assertFalse(orderEntity.getBookSet().contains(order));
                }
        );
        assertEquals(orderDto.getName(),  orderEntity.getName());
        assertEquals(orderDto.getDateCreate(),  orderEntity.getDateCreate());
    }

    @Test
    void testToEntity(){
        Set<Book> bookSet = new HashSet<>();
        bookSet.add(new Book("MyFirstBook", "KrylovichVI"));
        bookSet.add(new Book("MySecondBook", "KrylovichVI"));
        AuthUser authUser = new AuthUser("myTest", "123456", Role.USER, null);
        authUser.setId(1L);
        Order order = new Order(authUser.getId(), Date.valueOf(LocalDate.now()), Status.CONFIRMED, "order");
        order.setId(1L);
        order.setBookSet(bookSet);

        OrderEntity orderEntity = orderConverter.toEntity(order);

        assertNull(orderEntity.getAuthUser());
        assertEquals(orderEntity.getId(),  order.getId());
        assertEquals(orderEntity.getDateCreate(),  order.getDateCreate());
        assertNotNull(orderEntity.getBookSet());
    }
}
