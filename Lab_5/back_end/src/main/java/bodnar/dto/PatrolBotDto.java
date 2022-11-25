package bodnar.dto;

import bodnar.domain.CompleteSet;
import bodnar.domain.Teritory;
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
@Relation(itemRelation = "patrol_bot", collectionRelation = "patrol_bots")
public class PatrolBotDto extends RepresentationModel<PatrolBotDto> {
    private final Integer id;
    private final CompleteSet completeSet;
    private final Teritory teritory;
}
