package com.krylovichVI.dao.mapper;

import com.krylovichVI.dao.entity.BlackListEntity;
import com.krylovichVI.pojo.BlackList;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {AuthUserMapper.class})
public interface BlackListMapper extends AbstractMapper<BlackListEntity, BlackList> {

    @Mapping(source = "authUser.id", target = "authUserId")
    @Override
    BlackList toDto(BlackListEntity blackListEntity);

    @Override
    BlackListEntity toEntity(BlackList blackList);

    default BlackListEntity fromId(Long id){
        if(id == null){
            return null;
        }

        BlackListEntity blackListEntity = new BlackListEntity();
        blackListEntity.setId(id);
        return blackListEntity;
    }
}
