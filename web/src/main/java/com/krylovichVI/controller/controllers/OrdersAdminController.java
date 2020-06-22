package com.krylovichVI.controller.controllers;

import com.krylovichVI.pojo.Order;
import com.krylovichVI.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/adminOrders")
public class OrdersAdminController {
    private OrderService orderService;
    private final int FIRST_PAGE = 1;

    @Autowired
    public OrdersAdminController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping
    public String getAllOrders(@RequestParam(defaultValue = "1") String page, HttpServletRequest req){
        Integer currentPage = Integer.valueOf(page);

        Boolean idError = (Boolean) req.getSession().getAttribute("idError");
        req.getSession().removeAttribute("idError");

        List<Order> usersOrders = orderService.getOrderByPage(currentPage);
        req.setAttribute("countPage", orderService.getCountOfPage());
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("usersOrders", usersOrders);
        req.setAttribute("idError", idError);
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
}
