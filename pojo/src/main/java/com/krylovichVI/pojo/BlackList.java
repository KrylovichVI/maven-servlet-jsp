package com.krylovichVI.pojo;

import java.sql.Date;

public class BlackList {
    private Long id;
    private AuthUser authUser;
    private Date dateBlock;

    public BlackList(Long id, AuthUser authUser, Date dateBlock) {
        this.id = id;
        this.authUser = authUser;
        this.dateBlock = dateBlock;
    }

    public BlackList(AuthUser authUser, Date dateBlock) {
        this.authUser = authUser;
        this.dateBlock = dateBlock;
    }

    public Long getId() {
        return id;
    }

    public AuthUser getAuthUser() {
        return authUser;
    }

    public Date getDateBlock() {
        return dateBlock;
    }


    @Override
    public String toString() {
        return authUser + ", " + dateBlock;
    }
}
