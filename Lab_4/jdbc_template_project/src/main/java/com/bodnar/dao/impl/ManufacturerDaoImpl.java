package com.bodnar.dao.impl;

import com.bodnar.dao.ManufacturerDao;
import com.bodnar.domain.Manufacturer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerDaoImpl implements ManufacturerDao {
    private static final String FIND_ALL = "SELECT * FROM  manufacturer";
    private static final String CREATE = "INSERT manufacturer(name) VALUES (?)";
    private static final String UPDATE = "UPDATE manufacturer SET name=? WHERE id=?";
    private static final String DELETE = "DELETE FROM manufacturer WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM manufacturer WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Manufacturer> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Manufacturer.class));
    }

    @Override
    public Optional<Manufacturer> findById(Integer manufacturerId) {
        Optional<Manufacturer> manufacturer;
        try {
            manufacturer = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Manufacturer.class), manufacturerId));
        } catch (EmptyResultDataAccessException e) {
            manufacturer = Optional.empty();
        }
        return manufacturer;
    }

    @Override
    public int create(Manufacturer manufacturer) {
        return jdbcTemplate.update(CREATE, manufacturer.getName());
    }

    @Override
    public int update(Integer manufacturerId, Manufacturer manufacturer) {
        return jdbcTemplate.update(UPDATE, manufacturer.getName(), manufacturerId);
    }

    @Override
    public int delete(Integer manufacturerId) {
        return jdbcTemplate.update(DELETE, manufacturerId);
    }
}
