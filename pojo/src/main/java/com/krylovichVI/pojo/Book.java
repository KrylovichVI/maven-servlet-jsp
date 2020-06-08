package com.krylovichVI.pojo;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {"id"})
public class Book {
    private Long id;
    private String bookName;
    private String author;
    private List<Order> orderList = new ArrayList<>();

    public Book(String bookName, String author){
        this.bookName = bookName;
        this.author = author;
    }

}
