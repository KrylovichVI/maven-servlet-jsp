package com.krylovichVI.controller.controllers;

import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.pojo.Book;
import com.krylovichVI.pojo.Order;
import com.krylovichVI.service.AuthUserService;
import com.krylovichVI.service.BookService;
import com.krylovichVI.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@WebServlet(name = "ordersUserServlet", urlPatterns = "/userOrders")
@Controller
@RequestMapping("/userOrders")
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
    public String getUserOrders(HttpServletRequest req){
        AuthUser authUser = (AuthUser) req.getSession().getAttribute("authUser");
        List<Order> userOrders = orderService.getOrdersOfUser(authUser);
        List<Book> bookList = orderService.getBookByOrder();
        req.setAttribute("userOrders", userOrders);
        req.setAttribute("bookList", bookList);

        return "userOrders";
    }

    @PostMapping
    public String addOrder(HttpServletRequest req){
        String orderName = req.getParameter("orderName");
        List<Book> books = bookService.getListOfBookById(req.getParameterValues("bookId"));

        AuthUser authUser = (AuthUser) req.getSession().getAttribute("authUser");
        orderService.addOrder(orderName, authUser, books);

        return "redirect:/userOrders";
    }
}
