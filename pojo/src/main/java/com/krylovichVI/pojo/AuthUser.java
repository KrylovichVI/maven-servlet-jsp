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
@ToString
@Entity
@Table(name = "auth_user")
public class AuthUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(columnDefinition = "enum('USER', 'ADMIN')")
    private Role role;

    @OneToOne(mappedBy = "authUser", cascade = CascadeType.ALL)
    private User user;

    @OneToOne(mappedBy = "authUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private BlackList blackList;

    @OneToMany(mappedBy = "authUser",cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Order> orderList = new ArrayList<>();

    public AuthUser(String username, String password, Role role, User user){
        this.username = username;
        this.password = password;
        this.role = role;
        this.user = user;
    }
}
