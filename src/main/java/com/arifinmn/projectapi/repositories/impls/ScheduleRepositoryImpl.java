package com.arifinmn.projectapi.repositories.impls;

import com.arifinmn.projectapi.entities.Customers;
import com.arifinmn.projectapi.exceptions.EntityNotFoundException;
import com.arifinmn.projectapi.models.ScheduleModel;
import com.arifinmn.projectapi.models.responses.ScheduleResponse;
import com.arifinmn.projectapi.repositories.IScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ScheduleRepositoryImpl implements IScheduleRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void save(ScheduleModel entity) {
        if (entity.getId() == null) {
            String sql = "INSERT INTO schedules (status, customer_id) values (?,?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement stmt = connection.prepareStatement(sql, new String[]{"id"});
                stmt.setString(1, entity.getStatus());
                stmt.setInt(2, entity.getCustomer_id());
                return stmt;
            }, keyHolder);
            int id = Objects.requireNonNull(keyHolder.getKey()).intValue();
            entity.setId(id);
        } else {
            String sql = "UPDATE schedules SET  status =?,  customer_id=? where id=?";
            jdbcTemplate.update(connection -> {
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1, entity.getStatus().toString());
                stmt.setInt(2, entity.getCustomer_id());
                stmt.setInt(3, entity.getId());
                return stmt;
            });
        }
    }

    @Override
    public boolean removeById(Integer id) {
        String sqlQuery = "DELETE FROM schedules WHERE id=?";
        Integer status = jdbcTemplate.update(sqlQuery, id);
        if (status == 1) {
            return true;
        }
        return false;
    }

    @Override
    public ScheduleResponse findById(Integer id) {
        String sqlQuery = "SELECT sch.id, sch.status, cs.id as customer_id , cs.name, cs.email, cs.phone, cs.service " +
                "FROM schedules sch LEFT JOIN customers cs ON sch.customer_id = cs.id WHERE sch.id = ?";
        List<ScheduleResponse> scheduleList = jdbcTemplate.query(sqlQuery, new RowMapper<ScheduleResponse>() {
            @Override
            public ScheduleResponse mapRow(ResultSet resultSet, int i) throws SQLException {
                ScheduleResponse schedules = new ScheduleResponse();
                schedules.setId(resultSet.getInt("id"));
                schedules.setName(resultSet.getString("name"));
                schedules.setEmail(resultSet.getString("email"));
                schedules.setPhone(resultSet.getString("phone"));
                schedules.setService(resultSet.getString("service"));
                schedules.setStatus(resultSet.getString("status"));
                schedules.setCustomer_id(resultSet.getInt("customer_id"));
                return schedules;
            }
        }, new Object[]{id});
        return scheduleList.get(0);
    }

    @Override
    public ScheduleResponse findByCustomerId(Integer id) {
        String sqlQuery = "SELECT sch.id, sch.status, cs.id as customer_id , cs.name, cs.email, cs.phone, cs.service " +
                "FROM schedules sch LEFT JOIN customers cs ON sch.customer_id = cs.id WHERE cs.id = ?";
        List<ScheduleResponse> scheduleList = jdbcTemplate.query(sqlQuery, new RowMapper<ScheduleResponse>() {
            @Override
            public ScheduleResponse mapRow(ResultSet resultSet, int i) throws SQLException {
                ScheduleResponse schedules = new ScheduleResponse();
                schedules.setId(resultSet.getInt("id"));
                schedules.setName(resultSet.getString("name"));
                schedules.setEmail(resultSet.getString("email"));
                schedules.setPhone(resultSet.getString("phone"));
                schedules.setService(resultSet.getString("service"));
                schedules.setStatus(resultSet.getString("status"));
                schedules.setCustomer_id(resultSet.getInt("customer_id"));
                return schedules;
            }
        }, new Object[]{id});
        return scheduleList.get(0);
    }

    @Override
    public List<ScheduleResponse> findAll() {
        String sqlQuery = "SELECT sch.id, sch.status, cs.id as customer_id , cs.name, cs.email, cs.phone, cs.service " +
                "FROM schedules sch LEFT JOIN customers cs ON sch.customer_id = cs.id";
        List<ScheduleResponse> scheduleList = jdbcTemplate.query(sqlQuery, new RowMapper<ScheduleResponse>() {
            @Override
            public ScheduleResponse mapRow(ResultSet resultSet, int i) throws SQLException {
                ScheduleResponse schedules = new ScheduleResponse();
                schedules.setId(resultSet.getInt("id"));
                schedules.setName(resultSet.getString("name"));
                schedules.setEmail(resultSet.getString("email"));
                schedules.setPhone(resultSet.getString("phone"));
                schedules.setService(resultSet.getString("service"));
                schedules.setStatus(resultSet.getString("status"));
                schedules.setCustomer_id(resultSet.getInt("customer_id"));
                return schedules;
            }
        }, new Object[]{});
        return scheduleList;
    }

    @Override
    public Integer count() {
        Integer scheduleCount = jdbcTemplate.queryForObject("SELECT count(id) FROM schedules", Integer.class);
        return scheduleCount;
    }
}
