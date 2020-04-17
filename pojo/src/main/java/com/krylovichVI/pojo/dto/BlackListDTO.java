package com.krylovichVI.pojo.dto;

import com.krylovichVI.pojo.Role;

import java.sql.Date;

public class BlackListDTO {
    private Long id;
    private String username;
    private Date dateBlock;
    private Role role;

    public BlackListDTO(Long id, String username, Date dateBlock, Role role) {
        this.id = id;
        this.username = username;
        this.dateBlock = dateBlock;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public Date getDateBlock() {
        return dateBlock;
    }

    public Role getRole() {
        return role;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return  username +  ", " + dateBlock +  ", " + role;
    }
}
