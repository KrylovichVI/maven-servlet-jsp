package com.krylovichVI.dao.converters;

import com.krylovichVI.dao.entity.AuthUserEntity;
import com.krylovichVI.dao.entity.OrderEntity;
import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthUserConverter implements AbstractConverter<AuthUserEntity, AuthUser> {
    @Autowired
    private BlackListConverter blackListConverter;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private OrderConverter orderConverter;

    @Override
    public AuthUser toDto(AuthUserEntity authUserEntity){
        if(authUserEntity == null){
            return null;
        }

        AuthUser authUser = new AuthUser();

        authUser.setId(authUserEntity.getId());
        authUser.setUsername(authUserEntity.getUsername());
        authUser.setPassword(authUserEntity.getPassword());
        authUser.setRole(authUserEntity.getRole());
        authUser.setUser(userConverter.toDto(authUserEntity.getUser()));
        authUser.setBlackList(blackListConverter.toDto(authUserEntity.getBlackList()));
        authUser.setOrderList(orderEntityListToOrderList(authUserEntity.getOrderList()));

        return authUser;
    }

    @Override
    public AuthUserEntity toEntity(AuthUser authUser){
        if(authUser == null){
            return null;
        }

        AuthUserEntity authUserEntity = new AuthUserEntity();
        authUserEntity.setId(authUser.getId());
        authUserEntity.setUsername(authUser.getUsername());
        authUserEntity.setPassword(authUser.getPassword());
        authUserEntity.setRole(authUser.getRole());
        authUserEntity.setUser(userConverter.toEntity(authUser.getUser()));
        authUserEntity.setBlackList(blackListConverter.toEntity(authUser.getBlackList()));
        authUserEntity.setOrderList(orderListToOrderEntityList(authUser.getOrderList()));

        return authUserEntity;
    }

    public List<AuthUser> toDto(List<AuthUserEntity> authUserEntity){
        if(authUserEntity == null){
            return null;
        }
        List<AuthUser> list = new ArrayList<>(authUserEntity.size());
        for(AuthUserEntity entity : authUserEntity){
            list.add(toDto(entity));
        }

        return list;
    }

    protected List<Order> orderEntityListToOrderList(List<OrderEntity> orderEntityList){
        if(orderEntityList == null){
            return null;
        }

        List<Order> list = new ArrayList<>(orderEntityList.size());
        for(OrderEntity orderEntity : orderEntityList){
            list.add( orderConverter.toDto(orderEntity));
        }

        return list;
    }

    protected List<OrderEntity> orderListToOrderEntityList(List<Order> orderList){
        if(orderList == null){
            return null;
        }

        List<OrderEntity> list = new ArrayList<>(orderList.size());
        for(Order order : orderList){
            list.add(orderConverter.toEntity(order));
        }

        return list;
    }
}
