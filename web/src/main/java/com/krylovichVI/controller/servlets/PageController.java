//package com.krylovichVI.controller.servlets;
//
//import com.krylovichVI.controller.WebUtils;
//import com.krylovichVI.pojo.Book;
//import com.krylovichVI.service.BookService;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//
//
//@Controller
//@RequestMapping(path = "/page")
//public class PageController {
//    private final int FIRST_PAGE = 1;
//    private BookService bookService;
//
//    @GetMapping()
//    protected void mainPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String  currentPgs = req.getParameter("page");
//        Integer currentPage;
//        if(currentPgs == null){
//            currentPage = FIRST_PAGE;
//        } else {
//            currentPage = Integer.valueOf(currentPgs);
//        }
//        List<Book> books = bookService.getBooksByPage(currentPage);
//
//        req.setAttribute("booksList", books);
//        req.setAttribute("countPage", bookService.getCountOfPage());
//        req.setAttribute("currentPage", currentPage);
//
//        WebUtils.forwardToJsp("page", req, resp);
//    }
//
//    @PostMapping()
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String bookName = req.getParameter("bookName");
//        String author = req.getParameter("author");
//        if(!bookName.isEmpty() && !author.isEmpty()){
//            bookService.addBook(new Book(bookName, author));
//        }
//
//        WebUtils.sendRedirect( "/page", req, resp);
//    }
//}
