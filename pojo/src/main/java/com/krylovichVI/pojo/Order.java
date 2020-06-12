package com.krylovichVI.pojo;

import lombok.*;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(onlyExplicitlyIncluded = true)
public class Order {
    private Long id;
    private Date dateCreate;
    private Date dateUpdate;
    private Status status;
    private String name;
    private Long authUserId;
    private Set<Book> bookSet = new HashSet<>();

    public Order(Long authUserId, Date dateCreate, Status status, String name) {
        this.authUserId = authUserId;
        this.dateCreate = dateCreate;
        this.status = status;
        this.name = name;
    }
}
