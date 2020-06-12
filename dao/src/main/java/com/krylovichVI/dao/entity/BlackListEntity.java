package com.krylovichVI.dao.entity;

import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@AllArgsConstructor
@Entity
@Table(name = "black_list")
public class BlackListEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "authUser_Id", unique = true, nullable = false)
    private AuthUserEntity authUser;

    @Column(name = "date_block", updatable = false)
    private Date dateBlock;

    public BlackListEntity(Date dateBlock, AuthUserEntity authUser) {
        this.authUser = authUser;
        this.dateBlock = dateBlock;
    }

    public BlackListEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AuthUserEntity getAuthUser() {
        return authUser;
    }

    public void setAuthUser(AuthUserEntity authUser) {
        this.authUser = authUser;
    }

    public Date getDateBlock() {
        return dateBlock;
    }

    public void setDateBlock(Date dateBlock) {
        this.dateBlock = dateBlock;
    }
}
