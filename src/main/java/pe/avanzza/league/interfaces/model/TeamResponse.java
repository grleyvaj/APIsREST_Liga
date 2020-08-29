package pe.avanzza.league.interfaces.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import pe.avanzza.league.domain.entity.League;
import pe.avanzza.league.interfaces.shared.Constants;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Gloria R. Leyva Jerez
 */
@Data
@Builder
@Schema(name = "TeamResponse", description = Constants.teamRp_schema_description)
public class TeamResponse implements Serializable {

    private static final long serialVersionUID = 7452385045412249292L;

    @Schema(description = Constants.id_description, example = Constants.id_example)
    private int teamId;

    @NotBlank(message = "{notBlank.name}")
    @Size(max = 100, message = "{size.name.100}")
    @Schema(description = Constants.team_name_description, example = Constants.team_name_example, required = true)
    private String teamName;

    @Schema(description = Constants.team_passNumber_description, example = Constants.team_passNumber_example)
    private Integer passNumber;

    @Schema(description = Constants.team_faultNumber_description, example = Constants.team_faultNumber_example)
    private Integer faultNumber;

    @Schema(description = Constants.createdAt_description, example = Constants.createdAt_example)
    private Date createdAt;

    @Schema(description = Constants.updatedAt_description, example = Constants.updatedAt_example)
    private Date updatedAt;

    private List<League> leagues;
}
