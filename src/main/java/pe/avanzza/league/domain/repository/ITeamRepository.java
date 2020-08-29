package pe.avanzza.league.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pe.avanzza.league.domain.entity.League;
import pe.avanzza.league.domain.entity.Team;

import java.util.Optional;

/**
 * @author Gloria R. Leyva Jerez
 */
public interface ITeamRepository {

    Page<Team> findAll(Pageable pageable);

    Page<Team> findAllByLeagues(Pageable pageable, League league);

    Optional<Team> findById(int id);

    Team save(Team team);

    void delete(Team team);
}
