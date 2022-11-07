package com.bodnar.service.impl;

import com.bodnar.dao.TeritoryDao;
import com.bodnar.domain.Teritory;
import com.bodnar.service.TeritoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeritoryServiceImpl implements TeritoryService {
    @Autowired
    private TeritoryDao teritoryDao;

    @Override
    public List<Teritory> findAll() {
        return teritoryDao.findAll();
    }

    @Override
    public Optional<Teritory> findById(Integer teritoryId) {
        return teritoryDao.findById(teritoryId);
    }

    @Override
    public int create(Teritory teritory) {
        return teritoryDao.create(teritory);
    }

    @Override
    public int update(Integer teritoryId, Teritory teritory) {
        return teritoryDao.update(teritoryId, teritory);
    }

    @Override
    public int delete(Integer teritoryId) {
        return teritoryDao.delete(teritoryId);
    }
}
