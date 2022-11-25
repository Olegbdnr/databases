package bodnar.service.impl;

import bodnar.domain.CompleteSet;
import bodnar.domain.Manufacturer;
import bodnar.exception.CamerasExistForCompleteSetException;
import bodnar.exception.CompleteSetNotExistException;
import bodnar.exception.ManufacturerNotExistException;
import bodnar.exception.PatrolBotsExistForCompleteSetException;
import bodnar.repository.CompleteSetRepository;
import bodnar.repository.ManufacturerRepository;
import bodnar.service.CompleteSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CompleteSetServiceImpl implements CompleteSetService {
    @Autowired
    CompleteSetRepository completeSetRepository;

    @Autowired
    ManufacturerRepository manufacturerRepository;

    public CompleteSet findById(Integer id) {
        return completeSetRepository.findById(id).orElseThrow(() -> new CompleteSetNotExistException(id));
    }

    public List<CompleteSet> findAll() {
        return completeSetRepository.findAll();
    }

    @Transactional
    public CompleteSet create(CompleteSet completeSet) {
        completeSetRepository.save(completeSet);
        return completeSet;
    }

    @Transactional
    public CompleteSet create(CompleteSet completeSet, Integer manufacturerId) {
        Manufacturer manufacturer = manufacturerRepository.findById(manufacturerId)
                .orElseThrow(() -> new ManufacturerNotExistException(manufacturerId));
        completeSet.setManufacturer(manufacturer);
        completeSetRepository.save(completeSet);
        return completeSet;
    }

    @Transactional
    public void update(Integer id, CompleteSet completeSet) {
        CompleteSet newCompleteSet = completeSetRepository.findById(id)
                .orElseThrow(() -> new CompleteSetNotExistException(id));
        newCompleteSet.setName(completeSet.getName());
        newCompleteSet.setAverageSpeed(completeSet.getAverageSpeed());
        newCompleteSet.setBatteryReserve(completeSet.getBatteryReserve());
        newCompleteSet.setHeight(completeSet.getHeight());
        newCompleteSet.setLength(completeSet.getLength());
        newCompleteSet.setWidth(completeSet.getWidth());
        completeSetRepository.save(newCompleteSet);
    }

    @Transactional
    public void update(Integer id, CompleteSet completeSet, Integer manufacturerId) {
        CompleteSet newCompleteSet = completeSetRepository.findById(id)
                .orElseThrow(() -> new CompleteSetNotExistException(id));
        Manufacturer manufacturer = manufacturerRepository.findById(manufacturerId)
                .orElseThrow(() -> new ManufacturerNotExistException(manufacturerId));
        newCompleteSet.setName(completeSet.getName());
        newCompleteSet.setAverageSpeed(completeSet.getAverageSpeed());
        newCompleteSet.setBatteryReserve(completeSet.getBatteryReserve());
        newCompleteSet.setHeight(completeSet.getHeight());
        newCompleteSet.setLength(completeSet.getLength());
        newCompleteSet.setWidth(completeSet.getWidth());
        newCompleteSet.setManufacturer(manufacturer);
        completeSetRepository.save(newCompleteSet);
    }

    @Transactional
    public void delete(Integer id) {
        CompleteSet completeSet = completeSetRepository.findById(id)
                .orElseThrow(() -> new CompleteSetNotExistException(id));
        if (!completeSet.getCameras().isEmpty()) throw new CamerasExistForCompleteSetException(id);
        if (!completeSet.getPatrolBots().isEmpty()) throw new PatrolBotsExistForCompleteSetException(id);
        completeSetRepository.delete(completeSet);
    }
}
