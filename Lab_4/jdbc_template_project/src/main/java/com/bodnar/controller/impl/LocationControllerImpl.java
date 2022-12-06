package com.bodnar.controller.impl;

import com.bodnar.controller.LocationController;
import com.bodnar.domain.Employee;
import com.bodnar.domain.Location;
import com.bodnar.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationControllerImpl implements LocationController {
    @Autowired
    LocationService locationService;

    @Override
    public List<Location> findAll() {
        return locationService.findAll();
    }

    @Override
    public Optional<Location> findById(Integer locationId) {
        return locationService.findById(locationId);
    }

    @Override
    public int create(Location location) {
        return locationService.create(location);
    }

    @Override
    public int update(Integer locationId, Location location) {
        return locationService.update(locationId, location);
    }

    @Override
    public int delete(Integer locationId) {
        return locationService.delete(locationId);
    }
}
