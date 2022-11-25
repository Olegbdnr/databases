package bodnar.controller;

import bodnar.dto.assembler.LocationDtoAssembler;
import bodnar.domain.Location;
import bodnar.domain.Teritory;
import bodnar.dto.LocationDto;
import bodnar.dto.TeritoryDto;
import bodnar.dto.assembler.TeritoryDtoAssembler;
import bodnar.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/locations")
public class LocationController {
    @Autowired
    LocationService locationService;

    @Autowired
    LocationDtoAssembler locationDtoAssembler;

    @Autowired
    TeritoryDtoAssembler teritoryDtoAssembler;

    @GetMapping(path = "/{locationId}")
    public ResponseEntity<LocationDto> getLocation(@PathVariable Integer locationId) {
        Location location = locationService.findById(locationId);
        LocationDto locationDto = locationDtoAssembler.toModel(location);
        return new ResponseEntity<>(locationDto, HttpStatus.OK);
    }

    @GetMapping(path = "")
    public ResponseEntity<CollectionModel<LocationDto>> getAllLocations() {
        List<Location> locations = locationService.findAll();
        CollectionModel<LocationDto> locationDtos = locationDtoAssembler.toCollectionModel(locations);
        return new ResponseEntity<>(locationDtos, HttpStatus.OK);
    }

    @GetMapping(path = "/{locationId}/teritories")
    public ResponseEntity<CollectionModel<TeritoryDto>> getTeritoriesForLocation(@PathVariable Integer locationId) {
        Location location = locationService.findById(locationId);
        List<Teritory> teritoriesForLocation = location.getTeritories();
        CollectionModel<TeritoryDto> teritoryDtos = teritoryDtoAssembler.toCollectionModel(teritoriesForLocation);
        return new ResponseEntity<>(teritoryDtos, HttpStatus.OK);

    }

    @PostMapping(path = "")
    public ResponseEntity<LocationDto> addLocation(@RequestBody Location location) {
        Location newLocation = locationService.create(location);
        LocationDto locationDto = locationDtoAssembler.toModel(newLocation);
        return new ResponseEntity<>(locationDto, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{locationId}")
    public ResponseEntity<?> updateLocation(@RequestBody Location location, @PathVariable Integer locationId) {
        locationService.update(locationId, location);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/{locationId}")
    public ResponseEntity<?> deleteLocation(@PathVariable Integer locationId) {
        locationService.delete(locationId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
