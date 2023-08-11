package com.example.library.DAO;

import com.example.library.Models.Book;
import com.example.library.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index(){
        return jdbcTemplate.query("SELECT*FROM books", new BookMapper());
    }

    public Book show(int id){
        return jdbcTemplate.query("SELECT*FROM books WHERE id=?", new Object[]{id}, new BookMapper())
                .stream().findAny().orElse(null);
    }

    public void save(Book book){
        System.out.println(book.getPublished_on());
        jdbcTemplate.update("INSERT INTO books(title, author, published_on) VALUES (?, ?, ?)",
                book.getTitle(), book.getAuthor(), book.getPublished_on());
    }

    public void update(Book book, int id){
        jdbcTemplate.update("UPDATE books SET title=?, author=?, published_on=? WHERE id=?",
                book.getTitle(), book.getAuthor(), book.getPublished_on(), id);
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM books WHERE id=?", id);
    }

    public User belong(int id){
        return jdbcTemplate.query("SELECT*FROM users join books on users.id=books.user_id where books.id=?",
                new Object[]{id}, new UserMapper()).stream().findAny().orElse(null);
    }

    public void assign(int id, User user){
        jdbcTemplate.update("UPDATE books SET user_id=? where id=?", user.getId(), id);
    }

    public void free(int id){
        jdbcTemplate.update("UPDATE books SET user_id=NULL WHERE id=?", id);
    }
}
