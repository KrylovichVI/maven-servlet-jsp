package com.krylovichVI.pojo.dto;

import com.krylovichVI.pojo.Status;

import java.sql.Date;

public class OrderDTO {
    private Long id;
    private String orderName;
    private String username;
    private Date date;
    private Status status;

    public OrderDTO(Long id, String orderName, Date date, Status status) {
        this.id = id;
        this.orderName = orderName;
        this.date = date;
        this.status = status;
    }

    public OrderDTO(Long id, String orderName, String username, Date date, Status status) {
        this.id = id;
        this.orderName = orderName;
        this.username = username;
        this.date = date;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getOrderName() {
        return orderName;
    }

    public String getUsername() {
        return username;
    }

    public Date getDate() {
        return date;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return  orderName + ", " + ", " + username  + ", " + date + ", " + status;
    }
}
