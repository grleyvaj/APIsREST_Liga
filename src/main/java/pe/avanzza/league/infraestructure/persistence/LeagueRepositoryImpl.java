package pe.avanzza.league.infraestructure.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pe.avanzza.league.domain.entity.League;
import pe.avanzza.league.domain.entity.Team;
import pe.avanzza.league.domain.repository.ILeagueRepository;
import pe.avanzza.league.infraestructure.imports.ILeagueJPARepository;

import java.util.Optional;

/**
 * @author Gloria R. Leyva Jerez
 */
@Service
@Data
@AllArgsConstructor
public class LeagueRepositoryImpl implements ILeagueRepository {

    private final ILeagueJPARepository impl;

    @Override
    public Page<League> findAll(Pageable pageable) {
        return impl.findAll(pageable);
    }

    @Override
    public Page<League> findAllByTeams(Pageable pageable, Team team) {
        return impl.findAllByTeams(pageable, team);
    }

    @Override
    public Optional<League> findById(int id) {
        return impl.findById(id);
    }

    @Override
    public League save(League league) {
        return impl.save(league);
    }

    @Override
    public void delete(League league) {
        impl.delete(league);
    }
}
