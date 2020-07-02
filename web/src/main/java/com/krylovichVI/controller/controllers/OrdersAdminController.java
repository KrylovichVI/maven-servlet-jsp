package com.krylovichVI.controller.controllers;

import com.krylovichVI.pojo.Status;
import com.krylovichVI.pojo.dto.OrdersAdminDto;
import com.krylovichVI.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/adminOrders")
@PreAuthorize("hasAuthority('ADMIN')")
public class OrdersAdminController {
    private OrderService orderService;

    @Autowired
    public OrdersAdminController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping
    public String getAllOrders(@RequestParam(defaultValue = "1") Integer page, Model model){
        List<OrdersAdminDto> usersOrders = orderService.getOrdersAdminDto(
                orderService.getOrderByPage(page));
        model.addAttribute("countPage", orderService.getCountOfPage());
        model.addAttribute("currentPage", page);
        model.addAttribute("usersOrders", usersOrders);
        return "adminOrders";
    }

    @PostMapping
    public String updateStatusOrder(
            @RequestParam Long idName,
            @RequestParam Status status,
            Model model
            ){
        if(orderService.updateStatusOrder(idName, status.name()) == -1L) {
            model.addAttribute("idError", true);
        }

        return "redirect:/adminOrders";
    }

    @PostMapping("{orderId}")
    public String deleteOrderById(@PathVariable("orderId") Long orderId){
        orderService.deleteOrder(orderId);

        return "redirect:/adminOrders";
    }
}
