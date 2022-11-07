package com.bodnar.service.impl;

import com.bodnar.dao.ManufacturerDao;
import com.bodnar.domain.Manufacturer;
import com.bodnar.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {
    @Autowired
    private ManufacturerDao manufacturerDao;

    @Override
    public List<Manufacturer> findAll() {
        return manufacturerDao.findAll();
    }

    @Override
    public Optional<Manufacturer> findById(Integer manufacturerId) {
        return manufacturerDao.findById(manufacturerId);
    }

    @Override
    public int create(Manufacturer manufacturer) {
        return manufacturerDao.create(manufacturer);
    }

    @Override
    public int update(Integer manufacturerId, Manufacturer manufacturer) {
        return manufacturerDao.update(manufacturerId, manufacturer);
    }

    @Override
    public int delete(Integer manufacturerId) {
        return manufacturerDao.delete(manufacturerId);
    }
}
