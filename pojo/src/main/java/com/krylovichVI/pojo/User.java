package com.krylovichVI.pojo;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString(onlyExplicitlyIncluded = true)
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private Long authUserId;

    public User(String firstName, String lastName, String phone, String email, Long authUserId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.authUserId = authUserId;
    }
}
