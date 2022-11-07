package com.bodnar.controller.impl;

import com.bodnar.controller.TeritoryController;
import com.bodnar.domain.Teritory;
import com.bodnar.service.TeritoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeritoryControllerImpl implements TeritoryController {
    @Autowired
    TeritoryService teritoryService;

    @Override
    public List<Teritory> findAll() {
        return teritoryService.findAll();
    }

    @Override
    public Optional<Teritory> findById(Integer teritoryId) {
        return teritoryService.findById(teritoryId);
    }

    @Override
    public int create(Teritory teritory) {
        return teritoryService.create(teritory);
    }

    @Override
    public int update(Integer teritoryId, Teritory teritory) {
        return teritoryService.update(teritoryId, teritory);
    }

    @Override
    public int delete(Integer teritoryId) {
        return teritoryService.delete(teritoryId);
    }
}
