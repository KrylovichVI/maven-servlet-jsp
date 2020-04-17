package com.krylovichVI.pojo;

import java.sql.Date;

public class Order {
    private Long id;
    private AuthUser authUser;
    private Date date;
    private Status status;
    private String name;

    public Order(AuthUser authUser, Date date, Status status, String name) {
        this.authUser = authUser;
        this.date = date;
        this.status = status;
        this.name = name;
    }

    public Order(Long id, AuthUser authUser, Date date, Status status, String name) {
        this.id = id;
        this.authUser = authUser;
        this.date = date;
        this.status = status;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public AuthUser getAuthUser() {
        return authUser;
    }

    public Date getDate() {
        return date;
    }

    public Status getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return id + ", " + name + ", " + authUser + ", " + date + ", " + status;
    }
}
