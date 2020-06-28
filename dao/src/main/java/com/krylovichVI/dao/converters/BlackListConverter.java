package com.krylovichVI.dao.converters;

import com.krylovichVI.dao.entity.BlackListEntity;
import com.krylovichVI.dao.repository.AuthUserRepo;
import com.krylovichVI.pojo.BlackList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BlackListConverter implements AbstractConverter<BlackListEntity, BlackList> {
    @Autowired
    private AuthUserRepo authUserRepo;

    @Override
    public BlackList toDto(BlackListEntity blackListEntity){
        if(blackListEntity == null){
            return null;
        }

        BlackList blackList = new BlackList();

        blackList.setAuthUserId( blackListEntity.getAuthUser().getId());
        blackList.setId(blackListEntity.getId());
        blackList.setDateBlock(blackListEntity.getDateBlock());

        return blackList;
    }

    @Override
    public BlackListEntity toEntity(BlackList blackList){
        if(blackList == null){
            return null;
        }

        BlackListEntity blackListEntity = new BlackListEntity();

        if(blackList.getAuthUserId() != null){
            blackListEntity.setAuthUser(authUserRepo.findById(blackList.getAuthUserId()).orElse(null));
        }
        if(blackList.getId() != null){
            blackListEntity.setId(blackList.getId());
        }
        blackListEntity.setDateBlock(blackList.getDateBlock());

        return blackListEntity;
    }

    public List<BlackList> toDto(List<BlackListEntity> usersOfBlackList) {
        if(usersOfBlackList == null){
            return null;
        }

        List<BlackList> list = new ArrayList<>(usersOfBlackList.size());
        for(BlackListEntity users : usersOfBlackList){
            list.add(toDto(users));
        }

        return list;
    }
}
