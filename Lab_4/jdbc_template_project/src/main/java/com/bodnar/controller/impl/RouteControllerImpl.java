package com.bodnar.controller.impl;

import com.bodnar.controller.RouteController;
import com.bodnar.domain.Route;
import com.bodnar.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteControllerImpl implements RouteController {
    @Autowired
    RouteService routeService;

    @Override
    public List<Route> findAll() {
        return routeService.findAll();
    }

    @Override
    public Optional<Route> findById(Integer routeId) {
        return routeService.findById(routeId);
    }

    @Override
    public int create(Route route) {
        return routeService.create(route);
    }

    @Override
    public int update(Integer routeId, Route route) {
        return routeService.update(routeId, route);
    }

    @Override
    public int delete(Integer routeId) {
        return routeService.delete(routeId);
    }
}
