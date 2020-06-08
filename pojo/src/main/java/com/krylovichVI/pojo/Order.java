package com.krylovichVI.pojo;

import lombok.*;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString
public class Order {
    private Long id;
    private Date dateCreate;
    private Date dateUpdate;
    private Status status;
    private String name;
    private AuthUser authUser;
    private Set<Book> bookSet = new HashSet<>();

    public Order(AuthUser authUser, Date dateCreate, Status status, String name) {
        this.authUser = authUser;
        this.dateCreate = dateCreate;
        this.status = status;
        this.name = name;
    }
}
