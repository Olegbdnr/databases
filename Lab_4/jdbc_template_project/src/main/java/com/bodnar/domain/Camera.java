package com.bodnar.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Camera {
    private Integer id;
    private Integer completeSetId;
    private String name;
    private Integer rotationAngle;
    private Byte nightMode;
    private Byte faceRecognition;
    private Byte voiceRecognition;
}
