package bodnar.dto;

import bodnar.domain.Location;
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
@Relation(itemRelation = "teritory", collectionRelation = "teritories")
public class TeritoryDto extends RepresentationModel<TeritoryDto> {
    private final Integer id;
    private final String phone;
    private final Location location;
}
