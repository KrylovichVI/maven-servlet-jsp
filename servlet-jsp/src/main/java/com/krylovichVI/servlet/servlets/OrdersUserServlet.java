package com.krylovichVI.servlet.servlets;

import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.pojo.Book;
import com.krylovichVI.pojo.Order;
import com.krylovichVI.service.AuthUserService;
import com.krylovichVI.service.BookService;
import com.krylovichVI.service.OrderService;
import com.krylovichVI.service.impl.DefaultAuthUserService;
import com.krylovichVI.service.impl.DefaultBookService;
import com.krylovichVI.service.impl.DefaultOrderService;
import com.krylovichVI.servlet.WebUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ordersUserServlet", urlPatterns = "/userOrders")
public class OrdersUserServlet extends HttpServlet {
    private AuthUserService authUserService;
    private OrderService orderService;
    private BookService bookService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        orderService = DefaultOrderService.getInstance();
        authUserService = DefaultAuthUserService.getInstance();
        bookService = DefaultBookService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AuthUser authUser = (AuthUser) req.getSession().getAttribute("authUser");
        List<Order> userOrders = orderService.getOrdersOfUser(authUser);
        List<Book> bookList = orderService.getBookByOrder();
        req.setAttribute("userOrders", userOrders);
        req.setAttribute("bookList", bookList);
        WebUtils.forwardToJsp("userOrders", req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderName = req.getParameter("orderName");
        List<Book> books = bookService.getListOfBookById(req.getParameterValues("bookId"));

        AuthUser authUser = (AuthUser) req.getSession().getAttribute("authUser");
        orderService.addOrder(orderName, authUser, books);
        WebUtils.sendRedirect( "/userOrders", req, resp);
    }
}
