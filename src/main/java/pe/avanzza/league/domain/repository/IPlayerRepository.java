package pe.avanzza.league.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pe.avanzza.league.domain.entity.Player;
import pe.avanzza.league.domain.entity.Team;

import java.util.Optional;

/**
 * @author Gloria R. Leyva Jerez
 */
public interface IPlayerRepository {

    Page<Player> findAll(Pageable pageable);

    Optional<Player> findById(int id);

    Player save(Player player);

    void delete(Player player);

    Page<Player> findAllByTeam(Pageable pageable, Team team);
}
