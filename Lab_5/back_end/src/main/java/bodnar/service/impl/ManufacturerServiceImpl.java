package bodnar.service.impl;

import bodnar.domain.Manufacturer;
import bodnar.exception.CompleteSetsForManufacturerExistException;
import bodnar.exception.ManufacturerNotExistException;
import bodnar.repository.ManufacturerRepository;
import bodnar.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {
    @Autowired
    ManufacturerRepository manufacturerRepository;

    public Manufacturer findById(Integer id) {
        return manufacturerRepository.findById(id).orElseThrow(() -> new ManufacturerNotExistException(id));
    }

    public List<Manufacturer> findAll() {
        return manufacturerRepository.findAll();
    }

    @Transactional
    public Manufacturer create(Manufacturer manufacturer) {
        manufacturerRepository.save(manufacturer);
        return manufacturer;
    }

    @Transactional
    public void update(Integer id, Manufacturer manufacturer) {
        Manufacturer newManufacturer = manufacturerRepository.findById(id)
                .orElseThrow(() -> new ManufacturerNotExistException(id));
        newManufacturer.setName(manufacturer.getName());
        manufacturerRepository.save(newManufacturer);
    }

    @Transactional
    public void delete(Integer id) {
        Manufacturer manufacturer = manufacturerRepository.findById(id)
                .orElseThrow(() -> new ManufacturerNotExistException(id));
        if (!manufacturer.getCompleteSets().isEmpty()) throw new CompleteSetsForManufacturerExistException(id);
        manufacturerRepository.delete(manufacturer);
    }
}
