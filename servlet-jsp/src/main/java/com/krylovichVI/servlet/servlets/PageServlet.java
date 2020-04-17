package com.krylovichVI.servlet.servlets;

import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.service.AuthUserService;
import com.krylovichVI.service.BookService;
import com.krylovichVI.service.impl.DefaultAuthUserService;
import com.krylovichVI.service.impl.DefaultBookService;
import com.krylovichVI.pojo.Book;
import com.krylovichVI.servlet.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "pageServlet", urlPatterns = "/page")
public class PageServlet extends HttpServlet {
    private BookService bookService;
    private AuthUserService authUserService;

    @Override
    public void init() throws ServletException {
        super.init();
        bookService = DefaultBookService.getInstance();
        authUserService = DefaultAuthUserService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = bookService.getBooks();
        if(!books.isEmpty()) {
            req.setAttribute("booksList", books);
        }else{
            req.setAttribute("booksList", null);
        }

        WebUtils.forwardToJsp("page", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookName = req.getParameter("bookName");
        String author = req.getParameter("author");

        if(!bookName.isEmpty() && !author.isEmpty()){
            bookService.addBook(new Book(bookName, author));
        }

        WebUtils.sendRedirect( "/page", req, resp);
    }
}
