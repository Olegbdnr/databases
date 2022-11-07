package com.bodnar.service.impl;

import com.bodnar.dao.CameraDao;
import com.bodnar.domain.Camera;
import com.bodnar.service.CameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CameraServiceImpl implements CameraService {
    @Autowired
    private CameraDao cameraDao;

    @Override
    public List<Camera> findAll() {
        return cameraDao.findAll();
    }

    @Override
    public Optional<Camera> findById(Integer cameraId) {
        return cameraDao.findById(cameraId);
    }

    @Override
    public int create(Camera camera) {
        return cameraDao.create(camera);
    }

    @Override
    public int update(Integer cameraId, Camera camera) {
        return cameraDao.update(cameraId, camera);
    }

    @Override
    public int delete(Integer cameraId) {
        return cameraDao.delete(cameraId);
    }
}
