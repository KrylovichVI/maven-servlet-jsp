package com.krylovichVI.controller.controllers;

import com.krylovichVI.pojo.Book;
import com.krylovichVI.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/search")
public class SearchController {
    private SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }


    @GetMapping
    public String searchByBookName(
        @RequestParam(required = false, defaultValue = "") String filter,
        @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageble,
        Model model
    ){
        Page<Book> page = searchService.findBookBySearch(filter, pageble);
        model.addAttribute("page", page);
        model.addAttribute("filter", filter);

        return "search";
    }
}
