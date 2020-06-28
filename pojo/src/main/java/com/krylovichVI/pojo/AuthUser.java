package com.krylovichVI.pojo;


import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(onlyExplicitlyIncluded = true)
public class AuthUser {
    private Long id;
    private String username;
    private String password;
    private Role role;
    private User user;
    private BlackList blackList;
    private List<Order> orderList = new ArrayList<>();

    public AuthUser(String username, String password, Role role, User user){
        this.username = username;
        this.password = password;
        this.role = role;
        this.user = user;
    }

    public AuthUser(Long id, BlackList blackList){
        this.id = id;
        this.blackList = blackList;
    }

    public boolean isAdmin(){
        return role.equals(Role.ADMIN) ? true : false;
    }
}
