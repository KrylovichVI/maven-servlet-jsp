package com.krylovichVI.servlet.servlets;

import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.pojo.dto.OrderDTO;
import com.krylovichVI.service.AuthUserService;
import com.krylovichVI.service.OrderService;
import com.krylovichVI.service.impl.DefaultAuthUserService;
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

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        orderService = DefaultOrderService.getInstance();
        authUserService = DefaultAuthUserService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AuthUser authUser = (AuthUser) req.getSession().getAttribute("authUser");
        List<OrderDTO> userOrders = orderService.getOrdersOfUser(authUser);
        req.setAttribute("userOrders", userOrders);
        WebUtils.forwardToJsp("userOrders", req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderName = req.getParameter("orderName");
        AuthUser authUser = (AuthUser) req.getSession().getAttribute("authUser");
        orderService.addOrder(orderName, authUser);
        WebUtils.sendRedirect( "/userOrders", req, resp);
    }
}
