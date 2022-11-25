package com.bodnar.service.impl;

import com.bodnar.dao.LocationDao;
import com.bodnar.domain.Location;
import com.bodnar.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    private LocationDao locationDao;

    @Override
    public List<Location> findAll() {
        return locationDao.findAll();
    }

    @Override
    public Optional<Location> findById(Integer locationId) {
        return locationDao.findById(locationId);
    }

    @Override
    public int create(Location location) {
        return locationDao.create(location);
    }

    @Override
    public int update(Integer locationId, Location location) {
        return locationDao.update(locationId, location);
    }

    @Override
    public int delete(Integer locationId) {
        return locationDao.delete(locationId);
    }
}
