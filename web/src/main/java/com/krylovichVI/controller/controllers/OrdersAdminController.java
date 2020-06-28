package com.krylovichVI.controller.controllers;

import com.krylovichVI.pojo.Order;
import com.krylovichVI.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
        List<Order> usersOrders = orderService.getOrderByPage(page);
        model.addAttribute("countPage", orderService.getCountOfPage());
        model.addAttribute("currentPage", page);
        model.addAttribute("usersOrders", usersOrders);
        return "adminOrders";
    }

    @PostMapping
    public String updateStatusOrder(HttpServletRequest req){
        long idName = Long.parseLong(req.getParameter("idName"));
        String status = req.getParameter("status-order");

        if(orderService.updateStatusOrder(idName, status) == -1L) {
            req.getSession().setAttribute("idError", true);
        }

        return "redirect:/adminOrders";
    }

    @PostMapping("{orderId}")
    public String deleteOrderById(@PathVariable("orderId") Long orderId){
        orderService.deleteOrder(orderId);

        return "redirect:/adminOrders";
    }
}
