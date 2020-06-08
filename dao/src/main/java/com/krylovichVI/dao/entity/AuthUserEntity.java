package com.krylovichVI.dao.entity;


import com.krylovichVI.pojo.Role;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString
@Entity
@Table(name = "auth_user")
public class AuthUserEntity {
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
    private UserEntity user;

    @OneToOne(mappedBy = "authUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private BlackListEntity blackList;

    @OneToMany(mappedBy = "authUser",cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    private List<OrderEntity> orderList = new ArrayList<>();

    public AuthUserEntity(String username, String password, Role role, UserEntity user){
        this.username = username;
        this.password = password;
        this.role = role;
        this.user = user;
    }
}
