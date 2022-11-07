package com.bodnar.service.impl;

import com.bodnar.dao.PatrolBotDao;
import com.bodnar.domain.PatrolBot;
import com.bodnar.service.PatrolBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatrolBotServiceImpl implements PatrolBotService {
    @Autowired
    private PatrolBotDao patrolBotDao;

    @Override
    public List<PatrolBot> findAll() {
        return patrolBotDao.findAll();
    }

    @Override
    public Optional<PatrolBot> findById(Integer patrolBotId) {
        return patrolBotDao.findById(patrolBotId);
    }

    @Override
    public int create(PatrolBot patrolBot) {
        return patrolBotDao.create(patrolBot);
    }

    @Override
    public int update(Integer patrolBotId, PatrolBot patrolBot) {
        return patrolBotDao.update(patrolBotId, patrolBot);
    }

    @Override
    public int delete(Integer patrolBotId) {
        return patrolBotDao.delete(patrolBotId);
    }
}
