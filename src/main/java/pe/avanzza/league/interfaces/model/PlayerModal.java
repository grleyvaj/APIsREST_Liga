package pe.avanzza.league.interfaces.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import pe.avanzza.league.application.validator.CustomNotBlank;
import pe.avanzza.league.application.validator.OptionalId;
import pe.avanzza.league.interfaces.shared.Constants;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author Gloria R. Leyva Jerez
 */
@Data
@Schema(name = "PlayerModal", description = Constants.playerModalRq_schema_description)
public class PlayerModal implements Serializable {

    private static final long serialVersionUID = -4306346008232656808L;

    @CustomNotBlank(message = "{customNotBlank.name}")
    @Size(max = 150, message = "{size.name.150}")
    @Schema(description = Constants.player_name_description, example = Constants.player_name_example, required = true)
    private String playerName;

    @CustomNotBlank(message = "{customNotBlank.nationality}")
    @Size(max = 80, message = "{size.name.80}")
    @Schema(description = Constants.player_nationality_description, example = Constants.player_nationality_example, required = true)
    private String nationality;

    @Schema(description = Constants.player_height_description, example = Constants.player_height_example, required = true)
    private Double height;

    @Schema(description = Constants.player_rightHanded_description, example = Constants.player_rightHanded_example, required = true)
    private Boolean rightHanded;

    @OptionalId(message = "{optionalId.teamId}")
    @Schema(description = Constants.player_teamId_description, example = Constants.player_teamId_example)
    private Integer teamId;
}
