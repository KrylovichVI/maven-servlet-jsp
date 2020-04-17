package com.krylovichVI.dao.imp;

import com.krylovichVI.dao.BookDao;
import com.krylovichVI.dao.JDBCConnection;
import com.krylovichVI.pojo.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DefaultBookDao implements BookDao {
    private static final Logger logger = LoggerFactory.getLogger(DefaultBookDao.class);
    private static BookDao instance;

    private DefaultBookDao(){ }

    public static BookDao getInstance(){
        if(instance == null){
            instance = new DefaultBookDao();
        }
        return instance;
    }

    private Connection getConnection() {
        return JDBCConnection.getInstance().getConnection();
    }

    @Override
    public List<Book> getBooks() {
        final String sql = "select * from books order by id desc";
        List<Book> bookList = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ){
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                bookList.add(new Book(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("author")));
            }
        } catch (SQLException e) {
            logger.error("book {} error get books ", e);
            throw new RuntimeException(e);
        }
        return bookList;
    }

    @Override
    public long addBook(Book book) {
        final String sql = "insert into books(name, author) values(?, ?)";
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, book.getBookName());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.executeUpdate();
            logger.info("book {} add ", book.getAuthor(), book.getBookName());
            try(ResultSet generatedKeys = preparedStatement.getGeneratedKeys()){
                generatedKeys.next();
                return generatedKeys.getLong(1);
            }

        } catch (SQLException e) {
            logger.error("book {} error add ", book.getAuthor(), book.getBookName(), e);
            throw new RuntimeException(e);
        }
    }
}
