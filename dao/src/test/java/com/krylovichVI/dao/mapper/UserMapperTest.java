package com.krylovichVI.dao.mapper;

import com.krylovichVI.dao.config.HibernateConfig;
import com.krylovichVI.dao.converters.UserConverter;
import com.krylovichVI.dao.entity.AuthUserEntity;
import com.krylovichVI.dao.entity.UserEntity;
import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.pojo.Role;
import com.krylovichVI.pojo.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateConfig.class})
public class UserMapperTest {
    @Autowired
    private UserConverter userConverter;

    @Test
    void testToDto(){
        AuthUserEntity authUser = new AuthUserEntity("myTest", "123456", Role.USER, null);
        UserEntity user = new UserEntity( "name", "surname", "1234", "test@gmail.com", authUser);
        authUser.setUser(user);

        User userDto = userConverter.toDto(user);

        assertEquals(userDto.getLastName(),  user.getLastName());
        assertEquals(userDto.getFirstName(),  user.getFirstName());
        assertEquals(userDto.getEmail(),  user.getEmail());
        assertEquals(userDto.getPhone(),  user.getPhone());
    }

    @Test
    void testToEntity(){
        AuthUser authUser = new AuthUser("myTest", "123456", Role.USER, null);
        authUser.setId(1L);
        User user = new User( "name", "surname", "1234", "test@gmail.com", authUser.getId());
        user.setId(1L);
        authUser.setUser(user);

        UserEntity userEntity = userConverter.toEntity(user);

        assertEquals(userEntity.getLastName(),  user.getLastName());
        assertEquals(userEntity.getFirstName(),  user.getFirstName());
        assertEquals(userEntity.getEmail(),  user.getEmail());
        assertEquals(userEntity.getPhone(),  user.getPhone());
    }
}
