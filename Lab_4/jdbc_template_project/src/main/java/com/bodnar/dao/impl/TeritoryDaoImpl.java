package com.bodnar.dao.impl;

import com.bodnar.dao.TeritoryDao;
import com.bodnar.domain.Teritory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeritoryDaoImpl implements TeritoryDao {
    private static final String FIND_ALL = "SELECT * FROM  teritory";
    private static final String CREATE = "INSERT teritory(phone, location_id) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE teritory SET phone=?, location_id=? WHERE id=?";
    private static final String DELETE = "DELETE FROM route WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM route WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Teritory> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Teritory.class));
    }

    @Override
    public Optional<Teritory> findById(Integer teritoryId) {
        Optional<Teritory> teritory;
        try {
            teritory = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Teritory.class), teritoryId));
        } catch (EmptyResultDataAccessException e) {
            teritory = Optional.empty();
        }
        return teritory;
    }

    @Override
    public int create(Teritory teritory) {
        return jdbcTemplate.update(CREATE, teritory.getPhone(), teritory.getLocationId());
    }

    @Override
    public int update(Integer teritoryId, Teritory teritory) {
        return jdbcTemplate.update(UPDATE, teritory.getPhone(), teritory.getLocationId(), teritoryId);
    }

    @Override
    public int delete(Integer teritoryId) {
        return jdbcTemplate.update(DELETE, teritoryId);
    }
}
