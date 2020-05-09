package com.krylovichVI.pojo;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode()
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

    public Book(String bookName, String author){
        this.bookName = bookName;
        this.author = author;
    }

}
