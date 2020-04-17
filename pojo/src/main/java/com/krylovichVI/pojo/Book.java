package com.krylovichVI.pojo;

public class Book {
    private Long id;
    private String bookName;
    private String author;

    public Book(String bookName, String author) {
        this.bookName = bookName;
        this.author = author;
    }

    public Book(Long id, String firstName, String author) {
        this.id = id;
        this.bookName = firstName;
        this.author = author;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthor() {
        return author;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return bookName + ", " + author;
    }
}
