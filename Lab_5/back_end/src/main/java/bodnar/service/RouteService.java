package bodnar.service;

import bodnar.domain.Route;

public interface RouteService extends GeneralService<Route, Integer>{
    Route create(Route route, Integer teritoryId);

    void update(Integer id, Route route, Integer teritoryId);
}
