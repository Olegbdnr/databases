package com.bodnar.controller.impl;

import com.bodnar.controller.CompleteSetController;
import com.bodnar.domain.CompleteSet;
import com.bodnar.service.CityService;
import com.bodnar.service.CompleteSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompleteSetControllerImpl implements CompleteSetController {
    @Autowired
    CompleteSetService completeSetService;

    @Override
    public List<CompleteSet> findAll() {
        return completeSetService.findAll();
    }

    @Override
    public Optional<CompleteSet> findById(Integer completeSetId) {
        return completeSetService.findById(completeSetId);
    }

    @Override
    public int create(CompleteSet completeSet) {
        return completeSetService.create(completeSet);
    }

    @Override
    public int update(Integer completeSetId, CompleteSet completeSet) {
        return completeSetService.update(completeSetId, completeSet);
    }

    @Override
    public int delete(Integer completeSetId) {
        return completeSetService.delete(completeSetId);
    }
}
