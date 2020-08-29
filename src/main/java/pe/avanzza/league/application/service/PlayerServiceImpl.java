package pe.avanzza.league.application.service;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import pe.avanzza.league.application.adapter.PlayerAdapter;
import pe.avanzza.core.exception.ResourceNotFoundException;
import pe.avanzza.league.application.validator.ValidId;
import pe.avanzza.core.shared.URIConstant;
import pe.avanzza.league.domain.entity.Player;
import pe.avanzza.league.domain.entity.Team;
import pe.avanzza.league.domain.repository.IPlayerRepository;
import pe.avanzza.league.interfaces.model.PlayerModal;
import pe.avanzza.league.interfaces.model.PlayerRequest;
import pe.avanzza.league.interfaces.model.PlayerResponse;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author Gloria R. Leyva Jerez
 */
@Service
@Data
@Validated
public class PlayerServiceImpl {

    private static final String ENTITY_PLAYER = URIConstant.ENTITY_PLAYER;
    private static final String ENTITY_TEAM = URIConstant.ENTITY_TEAM;

    private final PlayerAdapter adapter;

    @Resource
    private IPlayerRepository playerRepository;

    /**
     * @param pageable Parameters for paging
     * @return List of players
     */
    public Page<PlayerResponse> getAllPlayers(Pageable pageable) {
        return playerRepository.findAll(pageable).map(adapter::mapTo);
    }

    /**
     * @param id Player's identifier
     * @return Player corresponding to the specified identifier
     */
    public PlayerResponse getPlayerById(@ValidId(message = "{validId.player}") int id) {
        return adapter.mapTo(getPlayer(id));
    }

    /**
     * @param team Player's team
     * @return Players corresponding to the specified team
     */
    public Page<PlayerResponse> getPlayersByTeam(Pageable pageable, Team team) {
        return playerRepository.findAllByTeam(pageable, team).map(adapter::mapTo);
    }

    /**
     * @param playerRequest Player information to be added
     * @return Added Player
     */
    public PlayerResponse addPlayer(@Valid PlayerRequest playerRequest) {
        Player playerSaved = playerRepository.save(adapter.mapTo(playerRequest));
        return adapter.mapTo(playerSaved);
    }

    /**
     * @param id            Player's identifier to be updated
     * @param playerRequest Information of the player to be updated
     * @return Updated Player
     */
    public PlayerResponse updatePlayer(@ValidId(message = "{validId.player}") int id,
                                       @Valid PlayerRequest playerRequest) {
        Player playerSaved = playerRepository.save(adapter.updateFrom(getPlayer(id), playerRequest));
        return adapter.mapTo(playerSaved);
    }

    public void updatePlayerPartial(@ValidId(message = "{validId.player}") int id,
                                    @Valid PlayerModal patch) {
        playerRepository.save(adapter.partialUpdateFrom(getPlayer(id), patch));
    }

    /**
     * @param id Player's identifier to be eliminated
     */
    public void deletePlayerById(@ValidId(message = "{validId.player}") int id) {
        playerRepository.delete(getPlayer(id));
    }

    public Player getPlayer(int id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY_PLAYER, "id", id));
    }
}
