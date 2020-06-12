package com.krylovichVI.dao.mapper;

import com.krylovichVI.dao.entity.BookEntity;
import com.krylovichVI.dao.entity.OrderEntity;
import com.krylovichVI.pojo.Book;
import com.krylovichVI.pojo.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(componentModel = "spring", uses = {BookMapper.class, AuthUserMapper.class})
public interface OrderMapper extends AbstractMapper<OrderEntity, Order> {

    @Mapping(source = "authUser.id", target = "authUserId")
    @Override
    Order toDto(OrderEntity orderEntity);

    Set<BookEntity> toEntity(Set<Book> books);

    Set<Book> toDto(Set<BookEntity> bookEntities);

    @Override
    OrderEntity toEntity(Order order);

    default OrderEntity fromId(Long id){
        if(id == null){
            return null;
        }

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(id);
        return orderEntity;
    }
}
