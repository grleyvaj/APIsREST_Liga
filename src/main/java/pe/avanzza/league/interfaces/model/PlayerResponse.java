package pe.avanzza.league.interfaces.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import pe.avanzza.league.interfaces.shared.Constants;

import java.io.Serializable;

/**
 * @author Gloria R. Leyva Jerez
 */
@Data
@Builder
@Schema(name = "PlayerRequest", description = Constants.playerRp_schema_description)
public class PlayerResponse implements Serializable {

    private static final long serialVersionUID = 3381276508541778105L;

    @Schema(description = Constants.id_description, example = Constants.id_example)
    private int playerId;

    @Schema(description = Constants.player_name_description, example = Constants.player_name_example)
    private String playerName;

    @Schema(description = Constants.player_nationality_description, example = Constants.player_nationality_example)
    private String nationality;

    @Schema(description = Constants.player_height_description, example = Constants.player_height_example)
    private Double height;

    @Schema(description = Constants.player_rightHanded_description, example = Constants.player_rightHanded_example)
    private Boolean rightHanded;

    @Schema(description = Constants.player_teamId_description, example = Constants.player_teamId_example)
    private Integer teamId;
}
