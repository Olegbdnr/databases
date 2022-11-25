package bodnar.dto;

import bodnar.domain.Teritory;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "route", collectionRelation = "routes")
public class RouteDto extends RepresentationModel<RouteDto> {
    private final Integer id;
    private final String name;
    private final BigDecimal length;
    private final Teritory teritory;
}
