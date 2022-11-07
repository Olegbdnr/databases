package com.bodnar.dao.impl;

import com.bodnar.dao.CompleteSetDao;
import com.bodnar.domain.CompleteSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompleteSetDaoImpl implements CompleteSetDao {
    private static final String FIND_ALL = "SELECT * FROM complete_set";
    private static final String CREATE = "INSERT complete_set(manufacturer_id, name, battery_reserve," +
            "average_speed, length, height) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE complete_set SET manufacturer_id=?,  name=?, battery_reserve=?," +
            "average_speed=?, length=?, height=? WHERE id=?";
    private static final String DELETE = "DELETE FROM complete_set WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM complete_set WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<CompleteSet> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(CompleteSet.class));
    }

    @Override
    public Optional<CompleteSet> findById(Integer completeSetId) {
        Optional<CompleteSet> completeSet;
        try {
            completeSet = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(CompleteSet.class), completeSetId));
        } catch (EmptyResultDataAccessException e) {
            completeSet = Optional.empty();
        }
        return completeSet;
    }

    @Override
    public int create(CompleteSet completeSet) {
        return jdbcTemplate.update(CREATE, completeSet.getManufacturerId(), completeSet.getName(),
                completeSet.getBatteryReserve(), completeSet.getAverageSpeed(),
                completeSet.getLength(), completeSet.getHeight());
    }

    @Override
    public int update(Integer completeSetId, CompleteSet completeSet) {
        return jdbcTemplate.update(UPDATE, completeSet.getManufacturerId(), completeSet.getName(),
                completeSet.getBatteryReserve(), completeSet.getAverageSpeed(),
                completeSet.getLength(), completeSet.getHeight(), completeSetId);
    }

    @Override
    public int delete(Integer completeSetId) {
        return jdbcTemplate.update(DELETE, completeSetId);
    }
}
