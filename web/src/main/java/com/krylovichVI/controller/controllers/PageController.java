package com.krylovichVI.controller.controllers;

import com.krylovichVI.pojo.Book;
import com.krylovichVI.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequestMapping(path = "/page")
public class PageController {
//    private final int FIRST_PAGE = 1;

    @Autowired
    public PageController(BookService bookService) {
        this.bookService = bookService;
    }

    private BookService bookService;


    @GetMapping()
    public String booksPage(@RequestParam(defaultValue = "1") Integer page, Model model){
        List<Book> books = bookService.getBooksByPage(page);

        model.addAttribute("booksList", books);
        model.addAttribute("countPage", bookService.getCountOfPage());
        model.addAttribute("currentPage", page);
        return "page";
    }

    @PostMapping()
    public String doPost(@RequestParam String bookName, @RequestParam String author){
        if(!bookName.isEmpty() && !author.isEmpty()){
            bookService.addBook(new Book(bookName, author));
        }

        return "redirect:/page";
    }
}
