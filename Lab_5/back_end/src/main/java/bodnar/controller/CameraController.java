package bodnar.controller;

import bodnar.domain.Camera;
import bodnar.dto.CameraDto;
import bodnar.dto.assembler.CameraDtoAssembler;
import bodnar.service.CameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/cameras")
public class CameraController {

    @Autowired
    CameraService cameraService;

    @Autowired
    CameraDtoAssembler cameraDtoAssembler;

    @GetMapping(path = "/{cameraId}")
    public ResponseEntity<CameraDto> getCamera(@PathVariable Integer cameraId) {
        Camera camera = cameraService.findById(cameraId);
        CameraDto cameraDto = cameraDtoAssembler.toModel(camera);
        return new ResponseEntity<>(cameraDto, HttpStatus.OK);
    }

    @GetMapping(path = "")
    public ResponseEntity<CollectionModel<CameraDto>> getAllCameras() {
        List<Camera> cameras = cameraService.findAll();
        CollectionModel<CameraDto> cameraDtos = cameraDtoAssembler.toCollectionModel(cameras);
        return new ResponseEntity<>(cameraDtos, HttpStatus.OK);
    }

    @PostMapping(path = "/{completeSetId}")
    public ResponseEntity<CameraDto> addCamera(@PathVariable Integer completeSetId, @RequestBody Camera camera) {
        Camera newCamera = cameraService.create(camera, completeSetId);
        CameraDto cameraDto = cameraDtoAssembler.toModel(newCamera);
        return new ResponseEntity<>(cameraDto, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{cameraId}/{completeSetId}")
    public ResponseEntity<?> updateCamera(@PathVariable Integer cameraId,
                                          @RequestBody Camera camera,
                                          @PathVariable Integer completeSetId) {
        cameraService.update(cameraId, camera, completeSetId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/{cameraId}")
    public ResponseEntity<?> deleteCamera(@PathVariable Integer cameraId) {
        cameraService.delete(cameraId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
