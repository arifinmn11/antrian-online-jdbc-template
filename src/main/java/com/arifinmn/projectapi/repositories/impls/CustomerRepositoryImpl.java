package com.arifinmn.projectapi.repositories.impls;

import com.arifinmn.projectapi.configs.constans.Service;
import com.arifinmn.projectapi.entities.Customers;
import com.arifinmn.projectapi.repositories.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Repository
public class CustomerRepositoryImpl implements ICustomerRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void save(Customers entity) {
        if (entity.getId() == null) {
            String sql = "INSERT INTO customers (name, email, phone, service) values (?,?,?,?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement stmt = connection.prepareStatement(sql, new String[]{"id"});
                stmt.setString(1, entity.getName());
                stmt.setString(2, entity.getEmail());
                stmt.setString(3, entity.getPhone());
                stmt.setString(4, entity.getService().toString());
                return stmt;
            }, keyHolder);
            int id = Objects.requireNonNull(keyHolder.getKey()).intValue();
            entity.setId(id);
        } else {
            String sql = "UPDATE customers SET email=?, name=?, phone=? , service=? where id=?";
            jdbcTemplate.update(connection -> {
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1, entity.getName());
                stmt.setString(2, entity.getEmail());
                stmt.setString(3, entity.getPhone());
                stmt.setString(4, entity.getService().toString());
                stmt.setInt(5, entity.getId());
                return stmt;
            });
        }
    }

    @Override
    public boolean removeById(Integer id) {
        String sqlQuery = "delete from customers where id=?";
        Integer status = jdbcTemplate.update(sqlQuery, id);
        if (status == 1) {
            return true;
        }
        return false;
    }

    @Override
    public Customers findById(Integer id) {
        List<Customers> customerList = jdbcTemplate.query("SELECT * FROM customers where id=?", new RowMapper<Customers>() {
            @Override
            public Customers mapRow(ResultSet resultSet, int i) throws SQLException {
                Customers customer = new Customers();
                customer.setId(resultSet.getInt("id"));
                customer.setName(resultSet.getString("name"));
                customer.setEmail(resultSet.getString("email"));
                customer.setPhone(resultSet.getString("phone"));
                customer.setService(Service.valueOf(resultSet.getString("service")));
                return customer;
            }
        }, new Object[]{id});
        return customerList.get(0);
    }

    @Override
    public List<Customers> findAll() {
        List<Customers> customerList = jdbcTemplate.query("SELECT * FROM customers", new BeanPropertyRowMapper().newInstance(Customers.class));
        return customerList;
    }

    @Override
    public Integer count() {
        Integer customerCount = jdbcTemplate.queryForObject("SELECT count(id) FROM customer", Integer.class);
        return customerCount;
    }
}
