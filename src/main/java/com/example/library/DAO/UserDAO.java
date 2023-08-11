package com.example.library.DAO;

import com.example.library.Models.Book;
import com.example.library.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> index(){
        return jdbcTemplate.query("SELECT*FROM users", new UserMapper());
    }

    public User show(int id){
        return jdbcTemplate.query("SELECT*FROM users WHERE id=?", new Object[]{id}, new UserMapper())
                .stream().findAny().orElse(null);
    }

    public void save(User user){
        jdbcTemplate.update("INSERT INTO users(full_name, birth) VALUES (?, ?)",
                user.getFull_name(), user.getBirth());
    }

    public void update(User user, int id){
        jdbcTemplate.update("UPDATE users SET full_name=?, birth=? WHERE id=?",
                user.getFull_name(), user.getBirth(), id);
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM users WHERE id=?", id);
    }

    public List<Book> books(int id){
        return jdbcTemplate.query("SELECT books.id, books.title, books.author, books.published_on, books.user_id FROM users join books on users.id=books.user_id where users.id=?",
                new Object[]{id}, new BookMapper());
    }
}
