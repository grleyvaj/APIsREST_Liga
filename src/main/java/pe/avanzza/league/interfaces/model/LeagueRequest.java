package pe.avanzza.league.interfaces.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import pe.avanzza.league.domain.entity.Team;
import pe.avanzza.league.interfaces.shared.Constants;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Gloria R. Leyva Jerez
 */
@Data
@Schema(name = "LeagueRequest", description = Constants.leagueRq_schema_description)
public class LeagueRequest implements Serializable {

    private static final long serialVersionUID = -2381622349177300084L;

    @NotBlank(message = "{notBlank.name}")
    @Size(max = 100, message = "{size.name.100}")
    @Schema(description = Constants.league_name_description, example = Constants.league_name_example, required = true)
    private String leagueName;

    @Future(message = "{future.startDate}")
    @Schema(description = Constants.league_startDate_description, example = Constants.league_startDate_example, required = true)
    private LocalDate startDate;

    @Future(message = "{future.endDate}")
    @Schema(description = Constants.league_endDate_description, example = Constants.league_endDate_example, required = true)
    private LocalDate endDate;

    @Min(value = 2, message = "{min.game}")
    @Schema(description = Constants.league_gameNumber_description, example = Constants.league_gameNumber_example, required = true)
    private int gameNumber;

    private List<Team> teams;
}
