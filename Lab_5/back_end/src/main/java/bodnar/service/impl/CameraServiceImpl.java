package bodnar.service.impl;

import bodnar.domain.Camera;
import bodnar.domain.CompleteSet;
import bodnar.exception.CameraNotExistException;
import bodnar.exception.CompleteSetNotExistException;
import bodnar.repository.CameraRepository;
import bodnar.repository.CompleteSetRepository;
import bodnar.service.CameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CameraServiceImpl implements CameraService {
    @Autowired
    CameraRepository cameraRepository;

    @Autowired
    CompleteSetRepository completeSetRepository;

    public Camera findById(Integer id) {
        return cameraRepository.findById(id).orElseThrow(() -> new CameraNotExistException(id));
    }

    public List<Camera> findAll() {
        return cameraRepository.findAll();
    }

    @Transactional
    public Camera create(Camera camera) {
        cameraRepository.save(camera);
        return camera;
    }

    @Transactional
    public Camera create(Camera camera, Integer completeSetId) {
        CompleteSet completeSet = completeSetRepository.findById(completeSetId)
                .orElseThrow(() -> new CompleteSetNotExistException(completeSetId));
        camera.setCompleteSet(completeSet);
        cameraRepository.save(camera);
        return camera;
    }

    @Transactional
    public void update(Integer id, Camera camera) {
        Camera newCamera = cameraRepository.findById(id).orElseThrow(() -> new CameraNotExistException(id));
        newCamera.setName(camera.getName());
        newCamera.setFaceRecognition(camera.getFaceRecognition());
        newCamera.setNightMode(camera.getNightMode());
        newCamera.setRotationAngle(camera.getRotationAngle());
        newCamera.setVoiceRecognition(camera.getVoiceRecognition());
        cameraRepository.save(newCamera);
    }

    @Transactional
    public void update(Integer id, Camera camera, Integer completeSetId) {
        Camera newCamera = cameraRepository.findById(id).orElseThrow(() -> new CameraNotExistException(id));
        CompleteSet completeSet = completeSetRepository.findById(completeSetId)
                .orElseThrow(() -> new CompleteSetNotExistException(completeSetId));
        newCamera.setName(camera.getName());
        newCamera.setFaceRecognition(camera.getFaceRecognition());
        newCamera.setNightMode(camera.getNightMode());
        newCamera.setRotationAngle(camera.getRotationAngle());
        newCamera.setVoiceRecognition(camera.getVoiceRecognition());
        newCamera.setCompleteSet(completeSet);
        cameraRepository.save(newCamera);
    }

    @Transactional
    public void delete(Integer id) {
        Camera camera = cameraRepository.findById(id).orElseThrow(() -> new CameraNotExistException(id));
        cameraRepository.delete(camera);
    }
}
