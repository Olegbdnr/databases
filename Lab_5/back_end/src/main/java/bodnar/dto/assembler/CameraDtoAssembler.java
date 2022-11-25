package bodnar.dto.assembler;

import bodnar.controller.CompleteSetController;
import bodnar.domain.Camera;
import bodnar.dto.CameraDto;
import bodnar.controller.CameraController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class CameraDtoAssembler implements RepresentationModelAssembler<Camera, CameraDto> {

    @Override
    public CameraDto toModel(Camera camera) {
        CameraDto cameraDto = CameraDto.builder()
                .id(camera.getId())
                .name(camera.getName())
                .faceRecognition(camera.getFaceRecognition())
                .rotationAngle(camera.getRotationAngle())
                .voiceRecognition(camera.getVoiceRecognition())
                .nightMode(camera.getNightMode())
                .build();
        Link selfLink = linkTo(methodOn(CameraController.class).getCamera(cameraDto.getId())).withSelfRel();
        Link completeSetLink = WebMvcLinkBuilder.linkTo(methodOn(CompleteSetController.class)
                .getCompleteSet(camera.getCompleteSet().getId())).withRel("completeSet");
        cameraDto.add(selfLink);
        cameraDto.add(completeSetLink);
        return cameraDto;
    }

    @Override
    public CollectionModel<CameraDto> toCollectionModel(Iterable <? extends Camera> cameras) {
        CollectionModel<CameraDto> cameraDtos = RepresentationModelAssembler.super.toCollectionModel(cameras);
        Link selfLink = linkTo(methodOn(CameraController.class).getAllCameras()).withSelfRel();
        cameraDtos.add(selfLink);
        return cameraDtos;
    }
}
