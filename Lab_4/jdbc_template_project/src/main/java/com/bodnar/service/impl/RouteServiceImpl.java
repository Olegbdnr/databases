package com.bodnar.service.impl;

import com.bodnar.dao.RouteDao;
import com.bodnar.domain.Route;
import com.bodnar.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteServiceImpl implements RouteService {
    @Autowired
    private RouteDao routeDao;

    @Override
    public List<Route> findAll() {
        return routeDao.findAll();
    }

    @Override
    public Optional<Route> findById(Integer routeId) {
        return routeDao.findById(routeId);
    }

    @Override
    public int create(Route route) {
        return routeDao.create(route);
    }

    @Override
    public int update(Integer routeId, Route route) {
        return routeDao.update(routeId, route);
    }

    @Override
    public int delete(Integer routeId) {
        return routeDao.delete(routeId);
    }
}
