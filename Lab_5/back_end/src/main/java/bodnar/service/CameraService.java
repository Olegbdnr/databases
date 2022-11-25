package bodnar.service;

import bodnar.domain.Camera;

public interface CameraService extends GeneralService<Camera, Integer>{
    Camera create(Camera camera, Integer completeSetId);

    void update(Integer id, Camera camera, Integer completeSetId);
}
