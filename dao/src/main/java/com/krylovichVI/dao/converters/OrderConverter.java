package com.krylovichVI.dao.converters;

import com.krylovichVI.dao.entity.BookEntity;
import com.krylovichVI.dao.entity.OrderEntity;
import com.krylovichVI.dao.repository.AuthUserRepo;
import com.krylovichVI.pojo.Book;
import com.krylovichVI.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class OrderConverter implements AbstractConverter<OrderEntity, Order> {
    @Autowired
    private AuthUserRepo authUserRepo;
    @Autowired
    private BookConverter bookConverter;

    @Override
    public Order toDto(OrderEntity orderEntity){
        if(orderEntity == null){
            return null;
        }

        Order order = new Order();

        order.setAuthUserId(orderEntity.getAuthUser().getId());
        order.setId(orderEntity.getId());
        order.setDateCreate(orderEntity.getDateCreate());
        order.setDateUpdate(orderEntity.getDateUpdate());
        order.setStatus(orderEntity.getStatus());
        order.setName(orderEntity.getName());
        order.setBookSet(toDto(orderEntity.getBookSet()));

        return order;
    }


    public Set<BookEntity> toEntity(Set<Book> books){
        if(books == null){
            return null;
        }

        Set<BookEntity> set = new HashSet<>(books.size());
        for(Book book : books) {
            set.add(bookConverter.toEntity(book));
        }

        return set;
    }


    public Set<Book> toDto(Set<BookEntity> bookEntities){
        if(bookEntities == null){
            return null;
        }

        Set<Book> set = new HashSet<>(bookEntities.size());
        for(BookEntity bookEntity : bookEntities ) {
            set.add(bookConverter.toDto( bookEntity));
        }

        return set;
    }

    @Override
    public OrderEntity toEntity(Order order){
        if(order == null){
            return null;
        }

        OrderEntity orderEntity = new OrderEntity();

        orderEntity.setAuthUser(authUserRepo.findById(order.getAuthUserId()).orElse(null));
        orderEntity.setId(order.getId());
        orderEntity.setDateCreate(order.getDateCreate());
        orderEntity.setDateUpdate(order.getDateUpdate());
        orderEntity.setStatus(order.getStatus());
        orderEntity.setName(order.getName());
        orderEntity.setBookSet(toEntity(order.getBookSet()));

        return orderEntity;
    }

    public List<Order> toDto(List<OrderEntity> orders) {
        if(orders == null){
            return null;
        }

        List<Order> list = new ArrayList<>(orders.size());
        for(OrderEntity entity : orders){
            list.add(toDto(entity));
        }
        return list;
    }
}
