package com.bodnar.controller.impl;

import com.bodnar.controller.CameraController;
import com.bodnar.domain.Camera;
import com.bodnar.service.CameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CameraControllerImpl implements CameraController {
    @Autowired
    CameraService cameraService;

    @Override
    public List<Camera> findAll() {
        return cameraService.findAll();
    }

    @Override
    public Optional<Camera> findById(Integer cameraId) {
        return cameraService.findById(cameraId);
    }

    @Override
    public int create(Camera camera) {
        return cameraService.create(camera);
    }

    @Override
    public int update(Integer cameraId, Camera camera) {
        return cameraService.update(cameraId, camera);
    }

    @Override
    public int delete(Integer cameraId) {
        return cameraService.delete(cameraId);
    }
}
