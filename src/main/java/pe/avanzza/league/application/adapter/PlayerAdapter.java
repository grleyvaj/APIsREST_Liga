package pe.avanzza.league.application.adapter;

import org.springframework.stereotype.Component;
import pe.avanzza.core.exception.ResourceNotFoundException;
import pe.avanzza.core.shared.URIConstant;
import pe.avanzza.league.domain.entity.Player;
import pe.avanzza.league.domain.repository.ITeamRepository;
import pe.avanzza.league.interfaces.model.PlayerModal;
import pe.avanzza.league.interfaces.model.PlayerRequest;
import pe.avanzza.league.interfaces.model.PlayerResponse;

import javax.annotation.Resource;

/**
 * @author Gloria R. Leyva Jerez
 */
@Component
public class PlayerAdapter {

    private static final String ENTITY_TEAM = URIConstant.ENTITY_TEAM;

    @Resource
    private ITeamRepository teamRepository;

    public Player mapTo(PlayerRequest request) {
        Player player = Player.builder()
                .playerName(request.getPlayerName())
                .nationality(request.getNationality())
                .height(request.getHeight())
                .rightHanded(request.getRightHanded())
                .build();
        if (request.getTeamId() != null) {
            player.setTeam(teamRepository.findById(request.getTeamId())
                    .orElseThrow(() -> new ResourceNotFoundException(ENTITY_TEAM, "id", request.getTeamId())));
        }
        return player;
    }

    public PlayerResponse mapTo(Player player) {
        PlayerResponse build = PlayerResponse.builder()
                .playerId(player.getPlayerId())
                .playerName(player.getPlayerName())
                .nationality(player.getNationality())
                .height(player.getHeight())
                .rightHanded(player.getRightHanded())
                .build();
        if (player.getTeam() != null)
            build.setTeamId(player.getTeam().getTeamId());
        return build;
    }

    public Player updateFrom(Player player, PlayerRequest request) {
        player.setPlayerName(request.getPlayerName());
        player.setNationality(request.getNationality());
        player.setHeight(request.getHeight());
        player.setRightHanded(request.getRightHanded());
        if (request.getTeamId() != null) {
            player.setTeam(teamRepository.findById(request.getTeamId())
                    .orElseThrow(() -> new ResourceNotFoundException(ENTITY_TEAM, "id", request.getTeamId())));
        }
        return player;
    }

    public Player partialUpdateFrom(Player entityToPatch, PlayerModal patch) {
        if (patch.getPlayerName() != null)
            entityToPatch.setPlayerName(patch.getPlayerName());
        if (patch.getNationality() != null)
            entityToPatch.setNationality(patch.getNationality());
        if (patch.getHeight() != null)
            entityToPatch.setHeight(patch.getHeight());
        if (patch.getRightHanded() != null)
            entityToPatch.setRightHanded(patch.getRightHanded());
        if (patch.getTeamId() != null)
            entityToPatch.setTeam(teamRepository.findById(patch.getTeamId())
                    .orElseThrow(() -> new ResourceNotFoundException(ENTITY_TEAM, "id", patch.getTeamId())));
        return entityToPatch;
    }
}
