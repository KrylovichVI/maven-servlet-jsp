package com.krylovichVI.controller.controllers;

import com.krylovichVI.controller.utils.ControllerUtils;
import com.krylovichVI.pojo.Book;
import com.krylovichVI.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping(path = "/book")
public class MainPageController {
    @Value("D:/IT_Academy/Library/uploads")
    private String uploadPath;

    @Autowired
    public MainPageController(BookService bookService) {
        this.bookService = bookService;
    }

    private BookService bookService;


    @GetMapping()
    public String booksPage(@RequestParam(defaultValue = "1") Integer page, Model model){
        List<Book> books = bookService.getBooksByPage(page);

        model.addAttribute("booksList", books);
        model.addAttribute("countPage", bookService.getCountOfPage());
        model.addAttribute("currentPage", page);
        return "mainPage";
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addBook(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam("bookName") String bookName,
            @RequestParam("author") String author,
            @RequestParam("file") MultipartFile file) throws IOException {
        if(!bookName.isEmpty() && !author.isEmpty()){
            Book newBook = new Book(bookName, author);
            ControllerUtils.saveFile(newBook, file, uploadPath);
            bookService.addBook(newBook);
        }

        return "redirect:/book?page=" + page;
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteBook(@PathVariable("id") Long bookId){
        bookService.deleteBook(bookId);
        return "redirect:/book";
    }
}
