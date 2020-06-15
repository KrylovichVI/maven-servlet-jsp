package com.krylovichVI.dao.converters;

import com.krylovichVI.dao.entity.UserEntity;
import com.krylovichVI.dao.repository.AuthUserRepo;
import com.krylovichVI.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements AbstractConverter<UserEntity, User> {
    @Autowired
    private AuthUserRepo authUserRepo;

    @Override
    public User toDto(UserEntity userEntity){
        if(userEntity == null){
            return null;
        }

        User user = new User();

        user.setAuthUserId(userEntity.getAuthUser().getId());
        user.setId(userEntity.getId());
        user.setFirstName(userEntity.getFirstName());
        user.setLastName(userEntity.getLastName());
        user.setPhone(userEntity.getPhone());
        user.setEmail(userEntity.getEmail());

        return user;
    }

    @Override
    public UserEntity toEntity(User user){
        if (user == null) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setAuthUser(authUserRepo.findById(user.getAuthUserId()).orElse(null));
        userEntity.setId(user.getId());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setPhone(user.getPhone());
        userEntity.setEmail(user.getEmail());

        return userEntity;
    }
}
