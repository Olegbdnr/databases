package bodnar.controller;

import bodnar.dto.CompleteSetDto;
import bodnar.dto.ManufacturerDto;
import bodnar.dto.assembler.ManufacturerDtoAssembler;
import bodnar.domain.CompleteSet;
import bodnar.domain.Manufacturer;
import bodnar.dto.assembler.CompleteSetDtoAssembler;
import bodnar.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/manufacturers")
public class ManufacturerController {

    @Autowired
    ManufacturerService manufacturerService;

    @Autowired
    ManufacturerDtoAssembler manufacturerDtoAssembler;

    @Autowired
    CompleteSetDtoAssembler completeSetDtoAssembler;

    @GetMapping(path = "/{manufacturerId}")
    public ResponseEntity<ManufacturerDto> getManufacturer(@PathVariable Integer manufacturerId) {
        Manufacturer manufacturer = manufacturerService.findById(manufacturerId);
        ManufacturerDto manufacturerDto = manufacturerDtoAssembler.toModel(manufacturer);
        return new ResponseEntity<>(manufacturerDto, HttpStatus.OK);
    }

    @GetMapping(path = "")
    public ResponseEntity<CollectionModel<ManufacturerDto>> getAllManufacturers() {
        List<Manufacturer> manufacturers = manufacturerService.findAll();
        CollectionModel<ManufacturerDto> manufacturerDtos = manufacturerDtoAssembler.toCollectionModel(manufacturers);
        return new ResponseEntity<>(manufacturerDtos, HttpStatus.OK);
    }

    @GetMapping(path = "/{manufacturerId}/completeSets")
    public ResponseEntity<CollectionModel<CompleteSetDto>> getCompleteSetsForManufacturer(@PathVariable Integer manufacturerId) {
        Manufacturer manufacturer = manufacturerService.findById(manufacturerId);
        List<CompleteSet> completeSetsForManufacturer = manufacturer.getCompleteSets();
        CollectionModel<CompleteSetDto> completeSetDtos = completeSetDtoAssembler.toCollectionModel(completeSetsForManufacturer);
        return new ResponseEntity<>(completeSetDtos, HttpStatus.OK);
    }

    @PostMapping(path = "")
    public ResponseEntity<ManufacturerDto> addManufacturer(@RequestBody Manufacturer manufacturer) {
        Manufacturer newManufacturer = manufacturerService.create(manufacturer);
        ManufacturerDto manufacturerDto = manufacturerDtoAssembler.toModel(newManufacturer);
        return new ResponseEntity<>(manufacturerDto, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{manufacturerId}")
    public ResponseEntity<?> updateManufacturer(@RequestBody Manufacturer manufacturer, @PathVariable Integer manufacturerId) {
        manufacturerService.update(manufacturerId, manufacturer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/{manufacturerId}")
    public ResponseEntity<?> deleteManufacturer(@PathVariable Integer manufacturerId) {
        manufacturerService.delete(manufacturerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
