package bodnar.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Camera {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "rotation_angle")
    private Integer rotationAngle;
    @Basic
    @Column(name = "night_mode")
    private Byte nightMode;
    @Basic
    @Column(name = "face_recognition")
    private Byte faceRecognition;
    @Basic
    @Column(name = "voice_recognition")
    private Byte voiceRecognition;
    @ManyToOne
    @JoinColumn(name = "complete_set_id", referencedColumnName = "id", nullable = false)
    private CompleteSet completeSet;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRotationAngle() {
        return rotationAngle;
    }

    public void setRotationAngle(Integer rotationAngle) {
        this.rotationAngle = rotationAngle;
    }

    public Byte getNightMode() {
        return nightMode;
    }

    public void setNightMode(Byte nightMode) {
        this.nightMode = nightMode;
    }

    public Byte getFaceRecognition() {
        return faceRecognition;
    }

    public void setFaceRecognition(Byte faceRecognition) {
        this.faceRecognition = faceRecognition;
    }

    public Byte getVoiceRecognition() {
        return voiceRecognition;
    }

    public void setVoiceRecognition(Byte voiceRecognition) {
        this.voiceRecognition = voiceRecognition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Camera camera = (Camera) o;
        return Objects.equals(id, camera.id) && Objects.equals(name, camera.name) && Objects.equals(rotationAngle, camera.rotationAngle) && Objects.equals(nightMode, camera.nightMode) && Objects.equals(faceRecognition, camera.faceRecognition) && Objects.equals(voiceRecognition, camera.voiceRecognition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, rotationAngle, nightMode, faceRecognition, voiceRecognition);
    }

    public CompleteSet getCompleteSet() {
        return completeSet;
    }

    public void setCompleteSet(CompleteSet completeSet) {
        this.completeSet = completeSet;
    }
}
