package pe.avanzza.league.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pe.avanzza.league.domain.entity.League;
import pe.avanzza.league.domain.entity.Team;

import java.util.Optional;

/**
 * @author Gloria R. Leyva Jerez
 */
public interface ILeagueRepository {

    Page<League> findAll(Pageable pageable);

    Page<League> findAllByTeams(Pageable pageable, Team team);

    Optional<League> findById(int id);

    League save(League league);

    void delete(League league);
}
