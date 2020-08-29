package pe.avanzza.league.application.adapter;

import org.springframework.stereotype.Component;
import pe.avanzza.core.shared.URIConstant;
import pe.avanzza.league.domain.entity.Team;
import pe.avanzza.league.domain.repository.ILeagueRepository;
import pe.avanzza.league.interfaces.model.TeamRequest;
import pe.avanzza.league.interfaces.model.TeamResponse;

import javax.annotation.Resource;

/**
 * @author Gloria R. Leyva Jerez
 */
@Component
public class TeamAdapter {

    private static final String ENTITY_LEAGUE = URIConstant.ENTITY_LEAGUE;

    @Resource
    private ILeagueRepository leagueRepository;

    public Team mapTo(TeamRequest request) {
        Team team = Team.builder()
                .teamName(request.getTeamName())
                .passNumber(request.getPassNumber())
                .faultNumber(request.getFaultNumber())
                .leagues(request.getLeagues())
                .build();
        return team;
    }

    public TeamResponse mapTo(Team team) {
        TeamResponse build = TeamResponse.builder()
                .teamId(team.getTeamId())
                .teamName(team.getTeamName())
                .passNumber(team.getPassNumber())
                .faultNumber(team.getFaultNumber())
                .leagues(team.getLeagues())
                .createdAt(team.getCreatedAt())
                .updatedAt(team.getUpdatedAt())
                .build();
        return build;
    }

    public Team updateFrom(Team team, TeamRequest request) {
        team.setTeamName(request.getTeamName());
        team.setPassNumber(request.getPassNumber());
        team.setFaultNumber(request.getFaultNumber());
        team.setLeagues(request.getLeagues());
        return team;
    }
}
