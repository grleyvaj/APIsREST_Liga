package pe.avanzza.league.application.adapter;

import org.springframework.stereotype.Component;
import pe.avanzza.league.domain.entity.League;
import pe.avanzza.league.interfaces.model.LeagueRequest;
import pe.avanzza.league.interfaces.model.LeagueResponse;

/**
 * @author Gloria R. Leyva Jerez
 */
@Component
public class LeagueAdapter {

    public League mapTo(LeagueRequest request) {
        League league = League.builder()
                .leagueName(request.getLeagueName())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .gameNumber(request.getGameNumber())
                .teams(request.getTeams())
                .build();
        return league;
    }

    public LeagueResponse mapTo(League league) {
        return LeagueResponse.builder()
                .leagueId(league.getLeagueId())
                .leagueName(league.getLeagueName())
                .startDate(league.getStartDate())
                .endDate(league.getEndDate())
                .gameNumber(league.getGameNumber())
                .teams(league.getTeams())
                .createdAt(league.getCreatedAt())
                .updatedAt(league.getUpdatedAt())
                .build();
    }

    public League updateFrom(League league, LeagueRequest request) {
        league.setLeagueName(request.getLeagueName());
        league.setStartDate(request.getStartDate());
        league.setEndDate(request.getEndDate());
        league.setGameNumber(request.getGameNumber());
        league.setTeams(request.getTeams());
        return league;
    }
}
