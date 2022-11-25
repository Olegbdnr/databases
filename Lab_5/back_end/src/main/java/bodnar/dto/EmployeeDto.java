package bodnar.dto;

import bodnar.domain.Teritory;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.sql.Date;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "employee", collectionRelation = "employees")
public class EmployeeDto extends RepresentationModel<CameraDto> {
    private final Integer id;
    private final String name;
    private final String surname;
    private final String gender;
    private final Date birthday;
    private final Teritory teritory;
}
