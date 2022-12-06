package bodnar.service.impl;

import bodnar.domain.CompleteSet;
import bodnar.domain.PatrolBot;
import bodnar.domain.Teritory;
import bodnar.exception.CompleteSetNotExistException;
import bodnar.exception.PatrolBotNotExistException;
import bodnar.exception.TeritoriesExistForLocationException;
import bodnar.exception.TeritoryNotFoundException;
import bodnar.repository.CompleteSetRepository;
import bodnar.repository.PatrolBotRepository;
import bodnar.repository.TeritoryRepository;
import bodnar.service.PatrolBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PatrolBotServiceImpl implements PatrolBotService {
    @Autowired
    PatrolBotRepository patrolBotRepository;

    @Autowired
    TeritoryRepository teritoryRepository;

    @Autowired
    CompleteSetRepository completeSetRepository;

    public PatrolBot findById(Integer id) {
        return patrolBotRepository.findById(id).orElseThrow(() -> new PatrolBotNotExistException(id));
    }

    public List<PatrolBot> findAll() {
        return patrolBotRepository.findAll();
    }

    @Transactional
    public PatrolBot create(PatrolBot patrolBot) {
        patrolBotRepository.save(patrolBot);
        return patrolBot;
    }

    @Transactional
    public PatrolBot create(PatrolBot patrolBot, Integer teritoryId, Integer completeSetId) {
        Teritory teritory = teritoryRepository.findById(teritoryId)
                .orElseThrow(() -> new TeritoryNotFoundException(teritoryId));
        CompleteSet completeSet = completeSetRepository.findById(completeSetId)
                .orElseThrow(() -> new CompleteSetNotExistException(completeSetId));
        patrolBot.setCompleteSet(completeSet);
        patrolBot.setTeritory(teritory);
        patrolBotRepository.save(patrolBot);
        return patrolBot;
    }

    @Transactional
    public void update(Integer id, PatrolBot patrolBot) {
        PatrolBot newPatrolBot = patrolBotRepository.findById(id)
                .orElseThrow(() -> new PatrolBotNotExistException(id));
        newPatrolBot.setTeritory(patrolBot.getTeritory());
        newPatrolBot.setCompleteSet(patrolBot.getCompleteSet());
        patrolBotRepository.save(newPatrolBot);
    }

    @Transactional
    public void update(Integer id, PatrolBot patrolBot, Integer teritoryId, Integer completeSetId) {
        PatrolBot newPatrolBot = patrolBotRepository.findById(id)
                .orElseThrow(() -> new PatrolBotNotExistException(id));
        Teritory teritory = teritoryRepository.findById(teritoryId)
                .orElseThrow(() -> new TeritoriesExistForLocationException(teritoryId));
        CompleteSet completeSet = completeSetRepository.findById(completeSetId)
                .orElseThrow(() -> new CompleteSetNotExistException(completeSetId));
        newPatrolBot.setTeritory(teritory);
        newPatrolBot.setCompleteSet(completeSet);
        patrolBotRepository.save(newPatrolBot);
    }

    @Transactional
    public void delete(Integer id) {
        PatrolBot patrolBot = patrolBotRepository.findById(id).orElseThrow(() -> new PatrolBotNotExistException(id));
        patrolBotRepository.delete(patrolBot);
    }

}
