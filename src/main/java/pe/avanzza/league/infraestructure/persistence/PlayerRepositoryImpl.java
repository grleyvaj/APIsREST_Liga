package pe.avanzza.league.infraestructure.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pe.avanzza.league.domain.entity.Player;
import pe.avanzza.league.domain.entity.Team;
import pe.avanzza.league.domain.repository.IPlayerRepository;
import pe.avanzza.league.infraestructure.imports.IPlayerJPARepository;

import java.util.Optional;

/**
 * @author Gloria R. Leyva Jerez
 */
@Service
@Data
@AllArgsConstructor
public class PlayerRepositoryImpl implements IPlayerRepository {

    private final IPlayerJPARepository impl;

    @Override
    public Page<Player> findAll(Pageable pageable) {
        return impl.findAll(pageable);
    }

    @Override
    public Optional<Player> findById(int id) {
        return impl.findById(id);
    }

    @Override
    public Player save(Player player) {
        return impl.save(player);
    }

    @Override
    public void delete(Player player) {
        impl.delete(player);
    }

    @Override
    public Page<Player> findAllByTeam(Pageable pageable, Team team) {
        return impl.findAllByTeam(pageable, team);
    }
}
