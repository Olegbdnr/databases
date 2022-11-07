package com.bodnar.dao.impl;

import com.bodnar.dao.LocationDao;
import com.bodnar.domain.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationDaoImpl implements LocationDao {
    private static final String FIND_ALL = "SELECT * FROM location";
    private static final String CREATE = "INSERT location(country, city," +
            "street, building_number) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE location SET country=?,  city=?, street=?," +
            "building_number=? WHERE id=?";
    private static final String DELETE = "DELETE FROM location WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM location WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Location> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Location.class));
    }

    @Override
    public Optional<Location> findById(Integer locationId) {
        Optional<Location> location;
        try {
            location = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Location.class), locationId));
        } catch (EmptyResultDataAccessException e) {
            location = Optional.empty();
        }
        return location;
    }

    @Override
    public int create(Location location) {
        return jdbcTemplate.update(CREATE, location.getCountry(), location.getCity(),
                location.getStreet(), location.getBuildingNumber());
    }

    @Override
    public int update(Integer locationId, Location location) {
        return jdbcTemplate.update(UPDATE, location.getCountry(), location.getCity(),
                location.getStreet(), location.getBuildingNumber(), locationId);
    }

    @Override
    public int delete(Integer locationId) {
        return jdbcTemplate.update(DELETE, locationId);
    }
}
