package bodnar.dto;

import bodnar.domain.Manufacturer;
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
@Relation(itemRelation = "complete_set", collectionRelation = "complete_sets")
public class CompleteSetDto extends RepresentationModel<CompleteSetDto> {
    private final Integer id;
    private final String name;
    private final BigDecimal batteryReserve;
    private final int averageSpeed;
    private final BigDecimal width;
    private final BigDecimal length;
    private final BigDecimal height;
    private final Manufacturer manufacturer;
}
