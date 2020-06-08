package com.krylovichVI.dao.entity;

import com.krylovichVI.pojo.Status;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString
@Entity
@Table(name = "orders")
public class OrderEntity {
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
    private AuthUserEntity authUser;

    @ManyToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable(name = "order_book",
            joinColumns = {@JoinColumn(name = "orderId")},
            inverseJoinColumns = {@JoinColumn(name = "bookId")}
    )
    private Set<BookEntity> bookSet = new HashSet<>();

    public OrderEntity(AuthUserEntity authUser, Date dateCreate, Status status, String name) {
        this.authUser = authUser;
        this.dateCreate = dateCreate;
        this.status = status;
        this.name = name;
    }
}
