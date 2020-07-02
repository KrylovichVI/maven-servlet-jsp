package com.krylovichVI.service;

import com.krylovichVI.pojo.Book;
import com.krylovichVI.service.utils.ConvertImageUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ConvertImageUtilsTest {
    @Test
    public void testOfConvertFilePathInBase64(){
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book(1L, "firstBook", "testAuthor", Collections.emptyList(), "path_1"));
        bookList.add(new Book(2L, "secondBook", "testAuthor", Collections.emptyList(), "path_2"));
        bookList.add(new Book(3L, "thirdBook", "testAuthor", Collections.emptyList(), "path_3"));

        List<Book> books = ConvertImageUtils.convertFilePathInBase64(bookList);

        for(int i = 0; i < books.size(); i++){
            assertTrue(books.get(i).getFilename().startsWith("data:image/png;base64,"));
        }
    }
}
