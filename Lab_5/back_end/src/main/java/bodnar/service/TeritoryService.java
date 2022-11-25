package bodnar.service;

import bodnar.domain.Teritory;

public interface TeritoryService extends GeneralService<Teritory, Integer>{
    Teritory create(Teritory teritory, Integer locationId);

    void update(Integer id, Teritory teritory, Integer locationId);
}
