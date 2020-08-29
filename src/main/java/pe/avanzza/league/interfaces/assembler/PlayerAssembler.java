package pe.avanzza.league.interfaces.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import pe.avanzza.league.interfaces.model.PlayerResponse;
import pe.avanzza.league.interfaces.rest.LeagueFreeAPI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Gloria R. Leyva Jerez
 */
@Component
public class PlayerAssembler implements RepresentationModelAssembler<PlayerResponse, EntityModel<PlayerResponse>> {

    @Override
    public EntityModel<PlayerResponse> toModel(PlayerResponse entity) {
        EntityModel<PlayerResponse> player = new EntityModel<>(entity,
                linkTo(methodOn(LeagueFreeAPI.class).getPlayerById(entity.getPlayerId())).withSelfRel());

        if (entity.getTeamId() != null)
            player.add(
                    linkTo(methodOn(LeagueFreeAPI.class).getTeamById(entity.getTeamId())).withRel("team"));
        return player;
    }
}
