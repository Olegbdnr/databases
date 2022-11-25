package bodnar.service;

import bodnar.domain.PatrolBot;

public interface PatrolBotService extends GeneralService<PatrolBot, Integer>{
    PatrolBot create(PatrolBot patrolBot, Integer teritoryId, Integer completeSetId);

    void update(Integer id, PatrolBot patrolBot, Integer teritoryId, Integer completeSetId);
}
