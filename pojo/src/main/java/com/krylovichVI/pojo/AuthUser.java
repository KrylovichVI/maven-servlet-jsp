package com.krylovichVI.pojo;


import java.util.Objects;

public class AuthUser {
    private Long id;
    private String username;
    private String password;
    private Role role;

    public AuthUser(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public AuthUser(Long id, String username, String password, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuthUser)) return false;
        AuthUser authUser = (AuthUser) o;
        return Objects.equals(username, authUser.username) &&
                Objects.equals(password, authUser.password) &&
                role == authUser.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

    @Override
    public String toString() {
        return username + ", " + password + ", " + role.name();
    }
}
