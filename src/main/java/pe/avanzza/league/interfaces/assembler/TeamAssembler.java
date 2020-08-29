package pe.avanzza.league.interfaces.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import pe.avanzza.league.interfaces.model.TeamResponse;
import pe.avanzza.league.interfaces.rest.LeagueFreeAPI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Gloria R. Leyva Jerez
 */
@Component
public class TeamAssembler implements RepresentationModelAssembler<TeamResponse, EntityModel<TeamResponse>> {

    @Override
    public EntityModel<TeamResponse> toModel(TeamResponse entity) {
        return new EntityModel<>(entity,
                linkTo(methodOn(LeagueFreeAPI.class).getTeamById(entity.getTeamId())).withSelfRel());
    }
}
