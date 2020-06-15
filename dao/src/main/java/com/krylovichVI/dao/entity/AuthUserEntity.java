package com.krylovichVI.dao.entity;


import com.krylovichVI.pojo.Role;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
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

    public AuthUserEntity(Long id, BlackListEntity blackList) {
        this.id = id;
        this.blackList = blackList;
    }

    public AuthUserEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public BlackListEntity getBlackList() {
        return blackList;
    }

    public void setBlackList(BlackListEntity blackList) {
        this.blackList = blackList;
    }

    public List<OrderEntity> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderEntity> orderList) {
        this.orderList = orderList;
    }
}
