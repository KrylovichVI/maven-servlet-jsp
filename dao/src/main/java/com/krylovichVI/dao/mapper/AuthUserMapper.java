package com.krylovichVI.dao.mapper;

import com.krylovichVI.dao.entity.AuthUserEntity;
import com.krylovichVI.pojo.AuthUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {BlackListMapper.class, UserMapper.class, OrderMapper.class})
public interface AuthUserMapper extends AbstractMapper<AuthUserEntity, AuthUser> {

    @Override
    AuthUser toDto(AuthUserEntity authUserEntity);

    @Override
    AuthUserEntity toEntity(AuthUser authUser);

    default AuthUserEntity fromId(Long id){
        if(id == null){
            return null;
        }
        AuthUserEntity authUserEntity = new AuthUserEntity();
        authUserEntity.setId(id);
        return authUserEntity;
    }

}
