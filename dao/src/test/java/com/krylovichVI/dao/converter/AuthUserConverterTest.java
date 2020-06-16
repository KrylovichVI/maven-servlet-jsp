package com.krylovichVI.dao.converter;

import com.krylovichVI.dao.config.HibernateConfig;
import com.krylovichVI.dao.converters.AuthUserConverter;
import com.krylovichVI.dao.entity.AuthUserEntity;
import com.krylovichVI.dao.entity.BlackListEntity;
import com.krylovichVI.dao.entity.OrderEntity;
import com.krylovichVI.dao.entity.UserEntity;
import com.krylovichVI.pojo.*;
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

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateConfig.class})
public class AuthUserConverterTest {
    @Autowired
    private AuthUserConverter authUserConverter;

    @Test
    void testToDto(){
        AuthUserEntity authUser = new AuthUserEntity("myTest", "123456", Role.USER, null);
        UserEntity userEmpty = new UserEntity( "", "", "", "", authUser);
        BlackListEntity blackListEntity = new BlackListEntity(Date.valueOf(LocalDate.now()), authUser);
        List<OrderEntity> orderEntityList = new ArrayList<>();
        orderEntityList.add(new OrderEntity(authUser, Date.valueOf(LocalDate.now()), Status.CONFIRMED, "first"));
        orderEntityList.add(new OrderEntity(authUser, Date.valueOf(LocalDate.now()), Status.CONFIRMED, "second"));
        orderEntityList.add(new OrderEntity(authUser, Date.valueOf(LocalDate.now()), Status.CONFIRMED, "third"));
        authUser.setOrderList(orderEntityList);
        authUser.setBlackList(blackListEntity);
        authUser.setUser(userEmpty);


        AuthUser authUserDto = authUserConverter.toDto(authUser);

        assertEquals(authUserDto.getUsername(),  authUser.getUsername());
        assertEquals(authUserDto.getPassword(),  authUser.getPassword());
        assertEquals(authUserDto.getRole(),  authUser.getRole());
        assertEquals(authUserDto.getUser().getId(),  authUser.getUser().getId());
        assertEquals(authUserDto.getUser().getAuthUserId(),  authUser.getUser().getAuthUser().getId());
        assertEquals(authUserDto.getBlackList().getId(),  authUser.getBlackList().getId());
        assertEquals(authUserDto.getOrderList().get(0).getName(), authUser.getOrderList().get(0).getName());
        assertEquals(authUserDto.getOrderList().get(1).getName(), authUser.getOrderList().get(1).getName());
        assertEquals(authUserDto.getOrderList().get(2).getName(), authUser.getOrderList().get(2).getName());
    }

    @Test
    void testToEntity(){
        AuthUser authUser = new AuthUser(1L, "myTest", "123456", Role.USER, null, null, null);
        User userEmpty = new User( "", "", "", "", authUser.getId());
        BlackList blackListEntity = new BlackList(Date.valueOf(LocalDate.now()), authUser.getId());
        List<Order> orderList = new ArrayList<>();
        orderList.add(new Order(authUser.getId(), Date.valueOf(LocalDate.now()), Status.CONFIRMED, "first"));
        orderList.add(new Order(authUser.getId(), Date.valueOf(LocalDate.now()), Status.CONFIRMED, "second"));
        orderList.add(new Order(authUser.getId(), Date.valueOf(LocalDate.now()), Status.CONFIRMED, "third"));
        authUser.setOrderList(orderList);
        authUser.setBlackList(blackListEntity);
        authUser.setUser(userEmpty);

        AuthUserEntity authUserEntity = authUserConverter.toEntity(authUser);

        assertEquals(authUserEntity.getUsername(),  authUser.getUsername());
        assertEquals(authUserEntity.getRole(),  authUser.getRole());
        assertEquals(authUserEntity.getUser().getId(),  authUser.getUser().getId());
        assertEquals(authUserEntity.getId(),  authUser.getBlackList().getAuthUserId());
        assertEquals(authUserEntity.getId(), authUser.getUser().getAuthUserId());
        assertEquals(authUserEntity.getOrderList().get(0).getName(),  authUser.getOrderList().get(0).getName());
        assertEquals(authUserEntity.getOrderList().get(1).getName(),  authUser.getOrderList().get(1).getName());
        assertEquals(authUserEntity.getOrderList().get(2).getName(),  authUser.getOrderList().get(2).getName());
    }
}
