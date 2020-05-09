package com.krylovichVI.servlet.servlets;

import com.krylovichVI.service.BookService;
import com.krylovichVI.service.impl.DefaultBookService;
import com.krylovichVI.servlet.WebUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "deleteBook", urlPatterns = "/deleteBook")
public class DeleteBookServlet extends HttpServlet {
    private BookService bookService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        bookService = DefaultBookService.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Enumeration<String> parameterNames = req.getParameterNames();
        Long bookId = null;
        if(parameterNames.hasMoreElements()){
            bookId =  Long.parseLong(parameterNames.nextElement());
        }
        bookService.deleteBook(bookId);
        WebUtils.sendRedirect("page", req, resp);
    }
}
