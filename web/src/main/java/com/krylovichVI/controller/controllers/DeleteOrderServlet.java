//package com.krylovichVI.controller.controllers;
//
//import com.krylovichVI.service.OrderService;
//import com.krylovichVI.service.impl.DefaultOrderService;
//import com.krylovichVI.controller.WebUtils;
//
//import javax.servlet.ServletConfig;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Enumeration;
//
//@WebServlet(name = "deleteOrderServlet", urlPatterns = "/deleteOrderServlet")
//public class DeleteOrderServlet extends HttpServlet {
//    private OrderService userService;
//
//    @Override
//    public void init(ServletConfig config) throws ServletException {
//        super.init(config);
//        userService = DefaultOrderService.getInstance();
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Long orderId = getLongOrderId(req);
//        userService.deleteOrder(orderId);
//        WebUtils.sendRedirect("/adminOrders", req, resp);
//    }
//
//    private Long getLongOrderId(HttpServletRequest req) {
//        Enumeration<String> parameterNames = req.getParameterNames();
//        Long orderId = null;
//        if(parameterNames.hasMoreElements()){
//           orderId =  Long.parseLong(parameterNames.nextElement());
//        }
//        return orderId;
//    }
//}
