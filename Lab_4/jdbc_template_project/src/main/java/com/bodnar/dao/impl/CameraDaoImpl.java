package com.bodnar.dao.impl;

import com.bodnar.dao.CameraDao;
import com.bodnar.domain.Camera;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CameraDaoImpl implements CameraDao {
    private static final String FIND_ALL = "SELECT * FROM camera";
    private static final String CREATE = "INSERT camera(complete_set_id, name, rotation_angle," +
            "night_mode, face_recognition, voice_recognition) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE camera SET complete_set_id=?,  name=?, rotation_angle=?," +
            "night_mode=?, face_recognition=?, voice_recognition=? WHERE id=?";
    private static final String DELETE = "DELETE FROM camera WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM camera WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Camera> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Camera.class));
    }

    @Override
    public Optional<Camera> findById(Integer cameraId) {
        Optional<Camera> camera;
        try {
            camera = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Camera.class), cameraId));
        } catch (EmptyResultDataAccessException e) {
            camera = Optional.empty();
        }
        return camera;
    }

    @Override
    public int create(Camera camera) {
        return jdbcTemplate.update(CREATE, camera.getCompleteSetId(), camera.getName(),
                camera.getRotationAngle(), camera.getNightMode(),
                camera.getFaceRecognition(), camera.getVoiceRecognition());
    }

    @Override
    public int update(Integer cameraId, Camera camera) {
        return jdbcTemplate.update(UPDATE, camera.getCompleteSetId(), camera.getName(),
                camera.getRotationAngle(), camera.getNightMode(),
                camera.getFaceRecognition(), camera.getVoiceRecognition(), cameraId);
    }

    @Override
    public int delete(Integer cameraId) {
        return jdbcTemplate.update(DELETE, cameraId);
    }
}
