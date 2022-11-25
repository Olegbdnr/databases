package bodnar.service.impl;

import bodnar.domain.Route;
import bodnar.domain.Teritory;
import bodnar.exception.RouteNotExistException;
import bodnar.exception.TeritoryNotFoundException;
import bodnar.repository.RouteRepository;
import bodnar.repository.TeritoryRepository;
import bodnar.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {
    @Autowired
    RouteRepository routeRepository;

    @Autowired
    TeritoryRepository teritoryRepository;

    public List<Route> findAll() {
        return routeRepository.findAll();
    }

    public Route findById(Integer id) {
        return routeRepository.findById(id).orElseThrow(() -> new RouteNotExistException(id));
    }

    @Transactional
    public Route create(Route route) {
        routeRepository.save(route);
        return route;
    }

    @Transactional
    public Route create(Route route, Integer teritoryId) {
        Teritory teritory = teritoryRepository.findById(teritoryId)
                .orElseThrow(() -> new TeritoryNotFoundException(teritoryId));
        route.setTeritory(teritory);
        routeRepository.save(route);
        return route;
    }

    @Transactional
    public void update(Integer id, Route route) {
        Route newRoute = routeRepository.findById(id).orElseThrow(() -> new RouteNotExistException(id));
        newRoute.setName(route.getName());
        newRoute.setLength(route.getLength());
        routeRepository.save(newRoute);
    }

    @Transactional
    public void update(Integer id, Route route, Integer teritoryId) {
        Route newRoute = routeRepository.findById(id).orElseThrow(() -> new RouteNotExistException(id));
        Teritory teritory = teritoryRepository.findById(teritoryId)
                .orElseThrow(() -> new TeritoryNotFoundException(teritoryId));
        newRoute.setName(route.getName());
        newRoute.setLength(route.getLength());
        newRoute.setTeritory(teritory);
        routeRepository.save(newRoute);
    }

    @Transactional
    public void delete(Integer id) {
        Route route = routeRepository.findById(id).orElseThrow(() -> new RouteNotExistException(id));
        routeRepository.delete(route);
    }
}
