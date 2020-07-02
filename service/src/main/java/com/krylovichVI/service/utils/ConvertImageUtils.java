package com.krylovichVI.service.utils;

import com.krylovichVI.pojo.Book;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ConvertImageUtils {
    public static List<Book> convertFilePathInBase64(List<Book> books){
        for(int i = 0; i < books.size(); i++){
            if(books.get(i).getFilename() != null){
                File file = new File(books.get(i).getFilename());
                byte[] content = new byte[0];
                try {
                    content = FileUtils.readFileToByteArray(file);
                } catch (IOException e) {
                }
                byte[] encode = Base64.encodeBase64(content);
                String filename = new String(encode);
                String imgAsBase64 = "data:image/png;base64," + filename;
                books.get(i).setFilename(imgAsBase64);
            }
        }
        return books;
    }
}
