package com.krylovichVI.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {"id"})
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String bookName;
    @Column(name = "author", nullable = false)
    private String author;

    @ManyToMany(mappedBy = "bookSet")
    private List<Order> orderList = new ArrayList<>();

    public Book(String bookName, String author){
        this.bookName = bookName;
        this.author = author;
    }

}
