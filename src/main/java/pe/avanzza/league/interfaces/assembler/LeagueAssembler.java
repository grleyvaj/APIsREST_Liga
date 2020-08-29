package pe.avanzza.league.interfaces.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import pe.avanzza.league.interfaces.model.LeagueResponse;
import pe.avanzza.league.interfaces.rest.LeagueFreeAPI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Gloria R. Leyva Jerez
 */
@Component
public class LeagueAssembler implements RepresentationModelAssembler<LeagueResponse, EntityModel<LeagueResponse>> {

    @Override
    public EntityModel<LeagueResponse> toModel(LeagueResponse entity) {
        return new EntityModel<>(entity,
                WebMvcLinkBuilder.linkTo(methodOn(LeagueFreeAPI.class).getLeagueById(entity.getLeagueId())).withSelfRel());
    }
}
