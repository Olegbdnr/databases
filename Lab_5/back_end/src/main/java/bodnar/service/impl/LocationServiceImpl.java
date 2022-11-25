package bodnar.service.impl;

import bodnar.domain.Location;
import bodnar.exception.LocationNotFoundException;
import bodnar.exception.TeritoriesExistForLocationException;
import bodnar.repository.LocationRepository;
import bodnar.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    LocationRepository locationRepository;

    public Location findById(Integer id) {
        return locationRepository.findById(id).orElseThrow(() -> new LocationNotFoundException(id));
    }

    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    @Transactional
    public Location create(Location location) {
        locationRepository.save(location);
        return location;
    }

    @Transactional
    public void update(Integer id, Location location) {
        Location newLocation = locationRepository.findById(id)
                .orElseThrow(() -> new LocationNotFoundException(id));
        newLocation.setCountry(location.getCountry());
        newLocation.setCity(location.getCity());
        newLocation.setStreet(location.getStreet());
        newLocation.setBuildingNumber(location.getBuildingNumber());
        locationRepository.save(newLocation);
    }

    @Transactional
    public void delete(Integer id) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new LocationNotFoundException(id));
        if (!location.getTeritories().isEmpty()) throw new TeritoriesExistForLocationException(id);
        locationRepository.delete(location);
    }
}
