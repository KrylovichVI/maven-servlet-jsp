package com.krylovichVI.dao.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString
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
}
