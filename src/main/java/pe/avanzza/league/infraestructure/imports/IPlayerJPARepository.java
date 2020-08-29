package pe.avanzza.league.infraestructure.imports;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pe.avanzza.league.domain.entity.Player;
import pe.avanzza.league.domain.entity.Team;

/**
 * @author Gloria R. Leyva Jerez
 */
@Repository
public interface IPlayerJPARepository extends PagingAndSortingRepository<Player, Integer> {

    Page<Player> findAllByTeam(Pageable pageable, Team team);
}
