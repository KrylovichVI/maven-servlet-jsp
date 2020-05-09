package com.krylovichVI.servlet.servlets;

import com.krylovichVI.pojo.Book;
import com.krylovichVI.service.BookService;
import com.krylovichVI.service.impl.DefaultBookService;
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
    private final int FIRST_PAGE = 1;
    private BookService bookService;

    @Override
    public void init() throws ServletException {
        super.init();
        bookService = DefaultBookService.getInstance();
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
        List<Book> books = bookService.getBooksByPage(currentPage);

        req.setAttribute("booksList", books);
        req.setAttribute("countPage", bookService.getCountOfPage());
        req.setAttribute("currentPage", currentPage);

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
