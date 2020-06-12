package com.krylovichVI.dao.mapper;

import com.krylovichVI.dao.entity.UserEntity;
import com.krylovichVI.pojo.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {AuthUserMapper.class})
public interface UserMapper extends AbstractMapper<UserEntity, User> {
    @Mapping(source = "authUser.id", target = "authUserId" )
    @Override
    User toDto(UserEntity userEntity);

    @Override
    UserEntity toEntity(User user);

    default UserEntity fromId(Long id){
        if(id == null){
            return null;
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setId(id);
        return userEntity;
    }
}
