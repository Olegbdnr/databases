package com.bodnar.controller.impl;

import com.bodnar.controller.ManufacturerController;
import com.bodnar.domain.Manufacturer;
import com.bodnar.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerControllerImpl implements ManufacturerController {
    @Autowired
    ManufacturerService manufacturerService;

    @Override
    public List<Manufacturer> findAll() {
        return manufacturerService.findAll();
    }

    @Override
    public Optional<Manufacturer> findById(Integer manufacturerId) {
        return manufacturerService.findById(manufacturerId);
    }

    @Override
    public int create(Manufacturer manufacturer) {
        return manufacturerService.create(manufacturer);
    }

    @Override
    public int update(Integer manufacturerId, Manufacturer manufacturer) {
        return manufacturerService.update(manufacturerId, manufacturer);
    }

    @Override
    public int delete(Integer manufacturerId) {
        return manufacturerService.delete(manufacturerId);
    }
}
