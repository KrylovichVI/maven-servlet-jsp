package com.krylovichVI.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;


@Data
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_create", updatable = false)
    private Date dateCreate;

    @Column(name = "date_update")
    private Date dateUpdate;

    @Enumerated(value = EnumType.STRING)
    @Column(columnDefinition = "ENUM('IN_PROCESSING', 'CONFIRMED', 'CANCELED')")
    private Status status;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne()
    @JoinColumn(name = "order_id")
    private AuthUser authUser;

    public Order(AuthUser authUser, Date dateCreate, Status status, String name) {
        this.authUser = authUser;
        this.dateCreate = dateCreate;
        this.status = status;
        this.name = name;
    }
}
