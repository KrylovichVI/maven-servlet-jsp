package com.krylovichVI.pojo.dto;

import com.krylovichVI.pojo.Order;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(onlyExplicitlyIncluded = true)
public class OrdersAdminDto {
    private String username;
    private Order order;
}
