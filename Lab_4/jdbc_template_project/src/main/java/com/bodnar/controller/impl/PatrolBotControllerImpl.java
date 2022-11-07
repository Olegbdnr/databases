package com.bodnar.controller.impl;

import com.bodnar.controller.PatrolBotController;
import com.bodnar.domain.PatrolBot;
import com.bodnar.service.PatrolBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatrolBotControllerImpl implements PatrolBotController {
    @Autowired
    PatrolBotService patrolBotService;

    @Override
    public List<PatrolBot> findAll() {
        return patrolBotService.findAll();
    }

    @Override
    public Optional<PatrolBot> findById(Integer patrolBotId) {
        return patrolBotService.findById(patrolBotId);
    }

    @Override
    public int create(PatrolBot patrolBot) {
        return patrolBotService.create(patrolBot);
    }

    @Override
    public int update(Integer patrolBotId, PatrolBot patrolBot) {
        return patrolBotService.update(patrolBotId, patrolBot);
    }

    @Override
    public int delete(Integer patrolBotId) {
        return patrolBotService.delete(patrolBotId);
    }
}
