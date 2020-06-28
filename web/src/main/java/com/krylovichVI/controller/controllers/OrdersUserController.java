package com.krylovichVI.controller.controllers;

import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.pojo.Book;
import com.krylovichVI.pojo.Order;
import com.krylovichVI.service.AuthUserService;
import com.krylovichVI.service.BookService;
import com.krylovichVI.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/userOrders")
@PreAuthorize("hasAuthority('USER')")
public class OrdersUserController {
    private AuthUserService authUserService;
    private OrderService orderService;
    private BookService bookService;

    @Autowired
    public OrdersUserController(AuthUserService authUserService, OrderService orderService, BookService bookService) {
        this.authUserService = authUserService;
        this.orderService = orderService;
        this.bookService = bookService;
    }

    @GetMapping
    public String getUserOrders(@AuthenticationPrincipal AuthUser authUser, Model model){
        List<Order> userOrders = orderService.getOrdersOfUser(authUser);
        List<Book> bookList = orderService.getBookByOrder();
        model.addAttribute("userOrders", userOrders);
        model.addAttribute("bookList", bookList);

        return "userOrders";
    }

    @PostMapping
    public String addOrder(
            @AuthenticationPrincipal AuthUser authUser,
            @RequestParam String orderName,
            @RequestParam String[] bookId
            ){
        List<Book> books = bookService.getListOfBookById(bookId);
        orderService.addOrder(orderName, authUser, books);

        return "redirect:/userOrders";
    }
}
