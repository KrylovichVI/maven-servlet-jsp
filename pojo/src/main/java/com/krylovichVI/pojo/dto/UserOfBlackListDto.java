package com.krylovichVI.pojo.dto;

import com.krylovichVI.pojo.Role;
import lombok.*;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(onlyExplicitlyIncluded = true)
public class UserOfBlackListDto {
    private String username;
    private Long blackListId;
    private Long authUserId;
    private Date dateBlock;
    private Role role;
}
