package com.krylovichVI.pojo;

import lombok.*;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString
public class BlackList {
    private Long id;
    private AuthUser authUser;
    private Date dateBlock;

    public BlackList(Date dateBlock, AuthUser authUser) {
        this.authUser = authUser;
        this.dateBlock = dateBlock;
    }
}
