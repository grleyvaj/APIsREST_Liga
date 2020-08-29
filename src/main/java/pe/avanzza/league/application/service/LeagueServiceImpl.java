package pe.avanzza.league.application.service;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import pe.avanzza.league.application.adapter.LeagueAdapter;
import pe.avanzza.core.exception.ResourceNotFoundException;
import pe.avanzza.league.application.validator.ValidId;
import pe.avanzza.core.shared.URIConstant;
import pe.avanzza.league.domain.entity.League;
import pe.avanzza.league.domain.entity.Team;
import pe.avanzza.league.domain.repository.ILeagueRepository;
import pe.avanzza.league.interfaces.model.LeagueRequest;
import pe.avanzza.league.interfaces.model.LeagueResponse;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author Gloria R. Leyva Jerez
 */
@Service
@Data
@Validated
public class LeagueServiceImpl {

    private static final String ENTITY_NAME = URIConstant.ENTITY_LEAGUE;

    private final LeagueAdapter adapter;

    @Resource
    private ILeagueRepository repository;

    /**
     * @param pageable Parameters for paging
     * @return List of Football league
     */
    public Page<LeagueResponse> getAllLeagues(Pageable pageable) {
        return repository.findAll(pageable).map(adapter::mapTo);
    }

    /**
     * @param pageable Parameters for paging
     * @param team     Team's identifier
     * @return List of leagues by team's identifier
     */
    public Page<LeagueResponse> getAllLeaguesByTeam(Pageable pageable, Team team) {
        return repository.findAllByTeams(pageable, team).map(adapter::mapTo);
    }

    /**
     * @param id League's identifier
     * @return League corresponding to the specified identifier
     */
    public LeagueResponse getLeagueById(@ValidId(message = "{validId.league}") int id) {
        return adapter.mapTo(getLeague(id));
    }

    /**
     * @param leagueRequest League information to be added
     * @return Added League
     */
    public LeagueResponse addLeague(@Valid LeagueRequest leagueRequest) {
        League leagueSaved = repository.save(adapter.mapTo(leagueRequest));
        return adapter.mapTo(leagueSaved);
    }

    /**
     * @param id            League's identifier to be updated
     * @param leagueRequest Information of the league to be updated
     * @return Updated League
     */
    public LeagueResponse updateLeague(@ValidId(message = "{validId.league}") int id,
                                       @Valid LeagueRequest leagueRequest) {
        League leagueSaved = repository.save(adapter.updateFrom(getLeague(id), leagueRequest));
        return adapter.mapTo(leagueSaved);
    }

    /**
     * @param id League's identifier to be eliminated
     */
    public void deleteLeagueById(@ValidId(message = "{validId.league}") int id) {
        repository.delete(getLeague(id));
    }

    public League getLeague(@ValidId(message = "{validId.league}") int id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY_NAME, "id", id));
    }
}
