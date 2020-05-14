package com.krylovichVI.servlet.servlets;

import com.krylovichVI.pojo.Order;
import com.krylovichVI.service.OrderService;
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

@WebServlet(name = "ordersAdminServlet", urlPatterns = "/adminOrders")
public class OrdersAdminServlet extends HttpServlet {
    private OrderService orderService;
    private final int FIRST_PAGE = 1;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        orderService = DefaultOrderService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String  currentPgs = req.getParameter("page");
        Integer currentPage;
        if(currentPgs == null){
            currentPage = FIRST_PAGE;
        } else {
            currentPage = Integer.valueOf(currentPgs);
        }


        Boolean idError = (Boolean) req.getSession().getAttribute("idError");
        req.getSession().removeAttribute("idError");

        List<Order> usersOrders = orderService.getOrderByPage(currentPage);
        req.setAttribute("countPage", orderService.getCountOfPage());
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("usersOrders", usersOrders);
        req.setAttribute("idError", idError);
        WebUtils.forwardToJsp("adminOrders", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long idName = Long.parseLong(req.getParameter("idName"));
        String status = req.getParameter("status-order");

        if(orderService.updateStatusOrder(idName, status) == -1L) {
            req.getSession().setAttribute("idError", true);
        }
        WebUtils.sendRedirect( "/adminOrders", req, resp);
    }
}
