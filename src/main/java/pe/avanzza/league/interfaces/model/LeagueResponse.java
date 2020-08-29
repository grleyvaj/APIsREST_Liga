package pe.avanzza.league.interfaces.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import pe.avanzza.league.domain.entity.Team;
import pe.avanzza.league.interfaces.shared.Constants;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * @author Gloria R. Leyva Jerez
 */
@Data
@Builder
@Schema(name = "LeagueResponse", description = Constants.leagueRp_schema_description)
public class LeagueResponse implements Serializable {

    private static final long serialVersionUID = 6690809197381596741L;

    @Schema(description = Constants.id_description, example = Constants.id_example)
    private int leagueId;

    @Schema(description = Constants.league_name_description, example = Constants.league_name_example)
    private String leagueName;

    @Schema(description = Constants.league_startDate_description, example = Constants.league_startDate_example)
    private LocalDate startDate;

    @Schema(description = Constants.league_endDate_description, example = Constants.league_endDate_example)
    private LocalDate endDate;

    @Schema(description = Constants.league_gameNumber_description, example = Constants.league_gameNumber_example)
    private int gameNumber;

    @Schema(description = Constants.createdAt_description, example = Constants.createdAt_example)
    private Date createdAt;

    @Schema(description = Constants.updatedAt_description, example = Constants.updatedAt_example)
    private Date updatedAt;

    private List<Team> teams;
}
