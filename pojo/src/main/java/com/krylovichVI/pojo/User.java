package com.krylovichVI.pojo;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
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
}
