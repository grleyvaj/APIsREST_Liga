package pe.avanzza.league.interfaces.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import pe.avanzza.league.domain.entity.League;
import pe.avanzza.league.interfaces.shared.Constants;

import java.io.Serializable;
import java.util.List;

/**
 * @author Gloria R. Leyva Jerez
 */
@Data
@Schema(name = "TeamRequest", description = Constants.teamRq_schema_description)
public class TeamRequest implements Serializable {

    private static final long serialVersionUID = 3704196469456219841L;

    @Schema(description = Constants.team_name_description, example = Constants.team_name_example, required = true)
    private String teamName;

    @Schema(description = Constants.team_passNumber_description, example = Constants.team_passNumber_example)
    private Integer passNumber;

    @Schema(description = Constants.team_faultNumber_description, example = Constants.team_faultNumber_example)
    private Integer faultNumber;

    private List<League> leagues;
}
