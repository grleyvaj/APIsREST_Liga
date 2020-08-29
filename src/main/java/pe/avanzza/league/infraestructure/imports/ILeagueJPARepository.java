package pe.avanzza.league.infraestructure.imports;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pe.avanzza.league.domain.entity.League;
import pe.avanzza.league.domain.entity.Team;

/**
 * @author Gloria R. Leyva Jerez
 */
@Repository
public interface ILeagueJPARepository extends PagingAndSortingRepository<League, Integer> {

    Page<League> findAllByTeams(Pageable pageable, Team team);
}
