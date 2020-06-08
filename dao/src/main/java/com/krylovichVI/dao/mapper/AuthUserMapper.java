package com.krylovichVI.dao.mapper;

import com.krylovichVI.dao.entity.AuthUserEntity;
import com.krylovichVI.pojo.AuthUser;
import org.mapstruct.Mapper;

@Mapper
public interface AuthUserMapper {
    AuthUser toDto(AuthUserEntity authUserEntity);
}
