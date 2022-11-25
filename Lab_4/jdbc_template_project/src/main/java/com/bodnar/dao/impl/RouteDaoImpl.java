package com.bodnar.dao.impl;

import com.bodnar.dao.RouteDao;
import com.bodnar.domain.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteDaoImpl implements RouteDao {
    private static final String FIND_ALL = "SELECT * FROM  route";
    private static final String CREATE = "INSERT route(teritory_id, patrol_bot_id, name, length) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE route SET teritory_id=?, patrol_bot_id=?, name=?, length=? WHERE id=?";
    private static final String DELETE = "DELETE FROM route WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM route WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Route> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Route.class));
    }

    @Override
    public Optional<Route> findById(Integer routeId) {
        Optional<Route> route;
        try {
            route = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Route.class), routeId));
        } catch (EmptyResultDataAccessException e) {
            route = Optional.empty();
        }
        return route;
    }

    @Override
    public int create(Route route) {
        return jdbcTemplate.update(CREATE, route.getTeritoryId(), route.getPatrolBotId(),
                route.getName(), route.getLength());
    }

    @Override
    public int update(Integer routeId, Route route) {
        return jdbcTemplate.update(UPDATE, route.getTeritoryId(), route.getPatrolBotId(),
                route.getName(), route.getLength(), routeId);
    }

    @Override
    public int delete(Integer routeId) {
        return jdbcTemplate.update(DELETE, routeId);
    }
}
