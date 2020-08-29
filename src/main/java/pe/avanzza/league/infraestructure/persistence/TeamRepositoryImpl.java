package pe.avanzza.league.infraestructure.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pe.avanzza.league.domain.entity.League;
import pe.avanzza.league.domain.entity.Team;
import pe.avanzza.league.domain.repository.ITeamRepository;
import pe.avanzza.league.infraestructure.imports.ITeamJPARepository;

import java.util.Optional;

/**
 * @author Gloria R. Leyva Jerez
 */
@Service
@Data
@AllArgsConstructor
public class TeamRepositoryImpl implements ITeamRepository {

    private final ITeamJPARepository impl;

    @Override
    public Page<Team> findAll(Pageable pageable) {
        return impl.findAll(pageable);
    }

    @Override
    public Page<Team> findAllByLeagues(Pageable pageable, League league) {
        return impl.findAllByLeagues(pageable, league);
    }

    @Override
    public Optional<Team> findById(int id) {
        return impl.findById(id);
    }

    @Override
    public Team save(Team team) {
        return impl.save(team);
    }

    @Override
    public void delete(Team team) {
        impl.delete(team);
    }
}
