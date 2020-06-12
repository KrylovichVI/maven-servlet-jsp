package com.krylovichVI.pojo;

import lombok.*;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(onlyExplicitlyIncluded = true)
public class BlackList {
    private Long id;
    private Long authUserId;
    private Date dateBlock;

    public BlackList(Date dateBlock, Long authUserId) {
        this.authUserId = authUserId;
        this.dateBlock = dateBlock;
    }
}
