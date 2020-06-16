package com.krylovichVI.dao.converter;

import com.krylovichVI.dao.config.HibernateConfig;
import com.krylovichVI.dao.converters.BlackListConverter;
import com.krylovichVI.dao.entity.AuthUserEntity;
import com.krylovichVI.dao.entity.BlackListEntity;
import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.pojo.BlackList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateConfig.class})
public class BlackListMapperTest{
    @Autowired
    private BlackListConverter blackListConverter;
    @Test
    void testToDto(){
        AuthUserEntity authUser = new AuthUserEntity(1L, null);
        BlackListEntity blackListEntity = new BlackListEntity(Date.valueOf(LocalDate.now()), authUser);
        authUser.setBlackList(blackListEntity);

        BlackList blackListDto = blackListConverter.toDto(blackListEntity);

        assertEquals(blackListDto.getAuthUserId(),  authUser.getId());
        assertEquals(blackListDto.getDateBlock(),  authUser.getBlackList().getDateBlock());
    }

    @Test
    void testToEntity(){
        AuthUser authUser = new AuthUser(1L, null);
        BlackList blackList = new BlackList(1L, authUser.getId(), Date.valueOf(LocalDate.now()));
        authUser.setBlackList(blackList);
        blackList.setAuthUserId(authUser.getId());

        BlackListEntity blackListEntity = blackListConverter.toEntity(blackList);

        assertNull(blackListEntity.getAuthUser());
        assertEquals(blackListEntity.getDateBlock(),  blackList.getDateBlock());
    }
}
