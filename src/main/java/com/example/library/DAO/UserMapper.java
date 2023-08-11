package com.example.library.DAO;

import com.example.library.Models.User;
import com.example.library.Models.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();

        user.setId(rs.getInt("id"));
        user.setFull_name(rs.getString("full_name"));
        user.setBirth(rs.getInt("birth"));

        return user;
    }
}
