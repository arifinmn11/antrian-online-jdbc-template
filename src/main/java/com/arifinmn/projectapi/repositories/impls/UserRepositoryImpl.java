package com.arifinmn.projectapi.repositories.impls;

import com.arifinmn.projectapi.entities.Users;
import com.arifinmn.projectapi.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepositoryImpl implements IUserRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Boolean existsByUsername(String username) {
        String sqlQuery = "SELECT count(id) INTO users WHERE username = ?";
        Integer countUsername = jdbcTemplate.update(sqlQuery, Integer.class);
        if (countUsername == 1) {
            return true;
        }
        return false;
    }

    @Override
    public Users findByUsername(String username) {
        String sqlQuery = "SELECT * FROM users WHERE username = ?";
        List<Users> userList = jdbcTemplate.query(sqlQuery, new RowMapper<Users>() {
            @Override
            public Users mapRow(ResultSet resultSet, int i) throws SQLException {
                Users userList = new Users();
                userList.setId(resultSet.getInt("id"));
                userList.setUsername(resultSet.getString("username"));
                userList.setPassword(resultSet.getString("password"));
                userList.setFullname(resultSet.getString("fullname"));
                return userList;
            }
        }, new Object[]{username});
        return userList.get(0);
    }

}
