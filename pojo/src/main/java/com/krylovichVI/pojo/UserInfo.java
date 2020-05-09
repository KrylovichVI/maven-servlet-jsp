package com.krylovichVI.pojo;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserInfo {
    private String phone;
    private String email;

}
