package bodnar.service.impl;

import bodnar.domain.Location;
import bodnar.domain.Teritory;
import bodnar.repository.LocationRepository;
import bodnar.repository.TeritoryRepository;
import bodnar.service.TeritoryService;
import bodnar.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TeritoryServiceImpl implements TeritoryService {

    @Autowired
    TeritoryRepository teritoryRepository;

    @Autowired
    LocationRepository locationRepository;

    public Teritory findById(Integer id) {
        return teritoryRepository.findById(id)
                .orElseThrow(() -> new TeritoryNotFoundException(id));
    }

    public List<Teritory> findAll() {
        return teritoryRepository.findAll();
    }

    @Transactional
    public Teritory create(Teritory teritory) {
        teritoryRepository.save(teritory);
        return teritory;
    }

    @Transactional
    public Teritory create(Teritory teritory, Integer locationId) {
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new LocationNotFoundException(locationId));
        teritory.setLocation(location);
        teritoryRepository.save(teritory);
        return teritory;
    }

    @Transactional
    public  void update(Integer id, Teritory teritory) {
        Teritory newTeritory = teritoryRepository.findById(id)
                .orElseThrow(() -> new TeritoryNotFoundException(id));
        newTeritory.setPhone(teritory.getPhone());
        teritoryRepository.save(newTeritory);
    }

    @Transactional
    public void update(Integer id, Teritory teritory, Integer locationId) {
        Teritory newTeritory = teritoryRepository.findById(id)
                .orElseThrow(() -> new TeritoryNotFoundException(id));
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new LocationNotFoundException(locationId));
        newTeritory.setPhone(teritory.getPhone());
        newTeritory.setLocation(location);
        teritoryRepository.save(newTeritory);
    }



    @Transactional
    public void delete(Integer id) {
        Teritory teritory = teritoryRepository.findById(id).orElseThrow(() -> new TeritoryNotFoundException(id));
        if (!teritory.getEmployees().isEmpty()) throw new EmployiesExistForTeritoryException(id);
        if (!teritory.getRoutes().isEmpty()) throw new RoutesExistForTeritoryException(id);
        if (!teritory.getPatrolBots().isEmpty()) throw new PatrolBotsExistForTeritoryException(id);
        teritoryRepository.delete(teritory);
    }
}
