package bodnar.dto;

import bodnar.domain.CompleteSet;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "camera", collectionRelation = "cameras")
public class CameraDto extends RepresentationModel<CameraDto> {
    private final Integer id;
    private final String name;
    private final int rotationAngle;
    private final byte nightMode;
    private final byte faceRecognition;
    private final byte voiceRecognition;
    private final CompleteSet completeSet;
}
