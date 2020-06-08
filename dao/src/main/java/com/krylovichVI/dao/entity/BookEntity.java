package com.krylovichVI.dao.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {"id"})
@Entity
@Table(name = "books")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String bookName;
    @Column(name = "author", nullable = false)
    private String author;

    @ManyToMany(mappedBy = "bookSet")
    private List<OrderEntity> orderList = new ArrayList<>();

    public BookEntity(String bookName, String author){
        this.bookName = bookName;
        this.author = author;
    }

}
