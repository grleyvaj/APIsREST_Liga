package pe.avanzza.league.application.service;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import pe.avanzza.league.application.adapter.TeamAdapter;
import pe.avanzza.core.exception.ResourceNotFoundException;
import pe.avanzza.league.application.validator.ValidId;
import pe.avanzza.core.shared.URIConstant;
import pe.avanzza.league.domain.entity.League;
import pe.avanzza.league.domain.entity.Team;
import pe.avanzza.league.domain.repository.ITeamRepository;
import pe.avanzza.league.interfaces.model.TeamRequest;
import pe.avanzza.league.interfaces.model.TeamResponse;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author Gloria R. Leyva Jerez
 */
@Service
@Data
@Validated
public class TeamServiceImpl {

    private static final String ENTITY_NAME = URIConstant.ENTITY_TEAM;

    private final TeamAdapter adapter;

    @Resource
    private ITeamRepository repository;

    /**
     * @param pageable Parameters for paging
     * @param league   League's identifier
     * @return List of teams by league's identifier
     */
    public Page<TeamResponse> getAllTeamsByLeagueId(Pageable pageable, League league) {
        return repository.findAllByLeagues(pageable, league).map(adapter::mapTo);
    }

    /**
     * @param pageable Parameters for paging
     * @return List of teams
     */
    public Page<TeamResponse> getAllTeams(Pageable pageable) {
        return repository.findAll(pageable).map(adapter::mapTo);
    }

    /**
     * @param id Team's identifier
     * @return Team corresponding to the specified identifier
     */
    public TeamResponse getTeamById(@ValidId(message = "{validId.team}") int id) {
        return adapter.mapTo(getTeam(id));
    }

    /**
     * @param teamRequest Team information to be added
     * @return Added Team
     */
    public TeamResponse addTeam(@Valid TeamRequest teamRequest) {
        Team teamSaved = repository.save(adapter.mapTo(teamRequest));
        return adapter.mapTo(teamSaved);
    }

    /**
     * @param id          Team's identifier to be updated
     * @param teamRequest Information of the team to be updated
     * @return Updated Team
     */
    public TeamResponse updateTeam(@ValidId(message = "{validId.team}") int id,
                                   @Valid TeamRequest teamRequest) {
        Team teamSaved = repository.save(adapter.updateFrom(getTeam(id), teamRequest));
        return adapter.mapTo(teamSaved);
    }

    /**
     * @param id Team's identifier to be eliminated
     */
    public void deleteTeamById(@ValidId(message = "{validId.team}") int id) {
        repository.delete(getTeam(id));
    }

    public Team getTeam(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY_NAME, "id", id));
    }
}
