package com.bodnar.service.impl;

import com.bodnar.dao.CompleteSetDao;
import com.bodnar.domain.CompleteSet;
import com.bodnar.service.CompleteSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompleteSetServiceImpl implements CompleteSetService {
    @Autowired
    private CompleteSetDao completeSetDao;

    @Override
    public List<CompleteSet> findAll() {
        return completeSetDao.findAll();
    }

    @Override
    public Optional<CompleteSet> findById(Integer completeSetId) {
        return completeSetDao.findById(completeSetId);
    }

    @Override
    public int create(CompleteSet completeSet) {
        return completeSetDao.create(completeSet);
    }

    @Override
    public int update(Integer completeSetId, CompleteSet completeSet) {
        return completeSetDao.update(completeSetId, completeSet);
    }

    @Override
    public int delete(Integer completeSetId) {
        return completeSetDao.delete(completeSetId);
    }
}
