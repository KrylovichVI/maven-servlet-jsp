package com.krylovichVI.pojo;

public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private AuthUser authUser;

    public User(String firstName, String lastName, String phone, String email, AuthUser authUser) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.authUser = authUser;
    }

    public User(Long id, String firstName, String lastName, String phone, String email, AuthUser authUser) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.authUser = authUser;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public AuthUser getAuthUser() {
        return authUser;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
