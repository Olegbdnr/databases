package bodnar.service;

import bodnar.domain.CompleteSet;

public interface CompleteSetService extends GeneralService<CompleteSet, Integer>{
    CompleteSet create(CompleteSet completeSet, Integer manufacturerId);

    void update(Integer id, CompleteSet completeSet, Integer manufacturerId);
}
