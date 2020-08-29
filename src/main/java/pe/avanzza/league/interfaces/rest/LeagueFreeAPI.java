package pe.avanzza.league.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.links.Link;
import io.swagger.v3.oas.annotations.links.LinkParameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.data.web.SortDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.avanzza.league.application.service.LeagueServiceImpl;
import pe.avanzza.league.application.service.PlayerServiceImpl;
import pe.avanzza.league.application.service.TeamServiceImpl;
import pe.avanzza.core.shared.URIConstant;
import pe.avanzza.league.interfaces.assembler.LeagueAssembler;
import pe.avanzza.league.interfaces.assembler.PlayerAssembler;
import pe.avanzza.league.interfaces.assembler.TeamAssembler;
import pe.avanzza.league.interfaces.model.*;
import pe.avanzza.league.interfaces.shared.Constants;
import pe.avanzza.league.interfaces.shared.SuccessfulContentHandler;
import pe.avanzza.security.throttling.Throttling;

import javax.annotation.Resource;

/**
 * @author Gloria R. Leyva Jerez
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = LeagueFreeAPI.ENTITY_API, produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = Constants.league_free_tag_name, description = Constants.league_free_tag_description)
@PreAuthorize("hasRole('FREE')")
public class LeagueFreeAPI {

    protected static final String ENTITY_API = URIConstant.ENTITY_API + URIConstant.API_FREE;
    private static final String URI_LEAGUE = URIConstant.URI_LEAGUE;
    private static final String ENTITY_LEAGUE = URIConstant.ENTITY_LEAGUE;

    private static final String URI_TEAM = URIConstant.URI_TEAM;
    private static final String ENTITY_TEAM = URIConstant.ENTITY_TEAM;

    private static final String URI_PLAYER = URIConstant.URI_PLAYER;
    private static final String ENTITY_PLAYER = URIConstant.ENTITY_PLAYER;

    private final LeagueAssembler leagueAssembler;
    private final PlayerAssembler playerAssembler;
    private final TeamAssembler teamAssembler;
    private final PagedResourcesAssembler<LeagueResponse> leagueResponsePagedResourcesAssembler;
    private final PagedResourcesAssembler<PlayerResponse> playerResponsePagedResourcesAssembler;
    private final PagedResourcesAssembler<TeamResponse> teamResponsePagedResourcesAssembler;

    private final SuccessfulContentHandler contentHandler;

    @Resource
    private LeagueServiceImpl leagueService;

    @Resource
    private PlayerServiceImpl playerService;

    @Resource
    private TeamServiceImpl teamService;

    /**
     * @param pageable Pageable with paging parameters, size and sort
     * @return RF1- List of leagues
     */
    @GetMapping(URI_LEAGUE)
    @Operation(summary = Constants.getAllLeague_op_summary, description = Constants.getAllLeague_op_description,
            tags = "LeagueFreeAPI", responses =
    @ApiResponse(content = @Content(schema = @Schema(implementation = LeagueResponse.class),
            examples = {@ExampleObject(value = Constants.pagedModel_league)}),
            links = {
                    @Link(name = "getLeagueById", operationRef = "#/paths/~1leagues~1{leagueId}/get",
                            description = Constants.getLeagueById_link1_description,
                            parameters = @LinkParameter(name = "leagueId", expression = "$request.path.leagueId")),
                    @Link(name = "self", operationId = "getAllLeagues", description = Constants.getLeagueById_link2_description,
                            parameters = {
                                    @LinkParameter(name = "pageable", expression = "$request.query.pageable")
                            })
            },
            responseCode = "200", description = Constants.getAllLeague_resp_description))
    @Throttling(timeFrameInSeconds = 60, calls = 3)
    public ResponseEntity<?> getAllLeagues(
            @Parameter(description = Constants.pageable_description, example = Constants.pageable_request)
            @PageableDefault(size = 5) @SortDefault.SortDefaults({@SortDefault(sort = "leagueId", direction = Sort.Direction.ASC)})
                    Pageable pageable) {

        Page<LeagueResponse> leagues = leagueService.getAllLeagues(pageable);
        PagedModel<EntityModel<LeagueResponse>> collModel = leagueResponsePagedResourcesAssembler.toModel(leagues, leagueAssembler);
        return ResponseEntity.ok(collModel);
    }

    /**
     * @param pageable Pageable with paging parameters, size and sort
     * @param leagueId League's identifier
     * @return RF2- List of teams belonging to this league
     */
    @GetMapping(URI_LEAGUE + "/{leagueId}/" + URI_TEAM)
    @Operation(summary = Constants.getAllTeamsByLeague_op_summary, description = Constants.getAllTeamsByLeague_op_description,
            tags = "LeagueFreeAPI", responses =
    @ApiResponse(content = @Content(schema = @Schema(implementation = TeamResponse.class),
            examples = {@ExampleObject(value = Constants.pagedModel_team)}),
            links = {
                    @Link(name = "getTeamById", operationRef = "#/paths/~1leagues~1{leagueId}~teams/get",
                            description = Constants.getTeamById_link1_description,
                            parameters = @LinkParameter(name = "leagueId", expression = "$request.path.leagueId")),
                    @Link(name = "self", operationId = "getAllTeamsByLeague", description = Constants.getTeamById_link2_description,
                            parameters = {
                                    @LinkParameter(name = "pageable", expression = "$request.query.pageable")
                            })
            },
            responseCode = "200", description = Constants.getAllTeamsByLeague_resp_description))
    @Throttling(timeFrameInSeconds = 60, calls = 3)
    public ResponseEntity<?> getAllTeamsByLeague(
            @Parameter(description = Constants.pageable_description, example = Constants.pageable_request)
            @PageableDefault(size = 5) @SortDefault.SortDefaults({@SortDefault(sort = "teamId", direction = Sort.Direction.ASC)})
                    Pageable pageable,
            @Parameter(description = Constants.updateLeague_pt1_description, required = true, example = "1")
            @PathVariable int leagueId) {

        Page<TeamResponse> teams = teamService.getAllTeamsByLeagueId(pageable, leagueService.getLeague(leagueId));
        PagedModel<EntityModel<TeamResponse>> collModel = teamResponsePagedResourcesAssembler.toModel(teams, teamAssembler);
        return ResponseEntity.ok(collModel);
    }

    /**
     * @param pageable Pageable with paging parameters, size and sort
     * @param teamId   Team's identifier
     * @return RF3- List of leagues in which this team participates
     */
    @GetMapping(URI_TEAM + "/{teamId}/" + URI_LEAGUE)
    @Operation(summary = Constants.getAllLeagueByTeam_op_summary, description = Constants.getAllLeagueByTeam_op_description,
            tags = "LeagueFreeAPI", responses =
    @ApiResponse(content = @Content(schema = @Schema(implementation = LeagueResponse.class),
            examples = {@ExampleObject(value = Constants.pagedModel_league)}),
            links = {
                    @Link(name = "getLeagueById", operationRef = "#/paths/~1leagues~1{leagueId}~teams/get",
                            description = Constants.getLeagueById_link1_description,
                            parameters = @LinkParameter(name = "leagueId", expression = "$request.path.leagueId")),
                    @Link(name = "self", operationId = "getAllLeaguesByTeam", description = Constants.getLeagueById_link2_description,
                            parameters = {
                                    @LinkParameter(name = "pageable", expression = "$request.query.pageable")
                            })
            },
            responseCode = "200", description = Constants.getAllLeagueByTeam_resp_description))
    @Throttling(timeFrameInSeconds = 60, calls = 3)
    public ResponseEntity<?> getAllLeaguesByTeam(
            @Parameter(description = Constants.pageable_description, example = Constants.pageable_request)
            @PageableDefault(size = 5) @SortDefault.SortDefaults({@SortDefault(sort = "leagueId", direction = Sort.Direction.ASC)})
                    Pageable pageable,
            @Parameter(description = Constants.updateTeam_pt1_description, required = true, example = "1")
            @PathVariable int teamId) {

        Page<LeagueResponse> leagues = leagueService.getAllLeaguesByTeam(pageable, teamService.getTeam(teamId));
        PagedModel<EntityModel<LeagueResponse>> collModel = leagueResponsePagedResourcesAssembler.toModel(leagues, leagueAssembler);
        return ResponseEntity.ok(collModel);
    }

    /**
     * @param pageable Pageable Pageable with paging parameters, size and sort
     * @param teamId   Team's identifier
     * @return RF4- List of all players of the specified team
     */
    @GetMapping(URI_TEAM + "/{teamId}/" + URI_PLAYER)
    @Operation(summary = Constants.getAllPlayersByTeam_op_summary, description = Constants.getAllPlayersByTeam_op_description,
            tags = "LeagueFreeAPI", responses =
    @ApiResponse(content = @Content(schema = @Schema(implementation = LeagueResponse.class),
            examples = {@ExampleObject(value = Constants.pagedModel_player)}),
            links = {
                    @Link(name = "getPlayerById", operationRef = "#/paths/~1equipos~1{teamId}~jugadores~1/get",
                            description = Constants.getPlayerById_link1_description,
                            parameters = @LinkParameter(name = "teamId", expression = "$request.path.teamId"))
            },
            responseCode = "200", description = Constants.getAllPlayersByTeam__resp_description))
    @Throttling(timeFrameInSeconds = 60, calls = 3)
    public ResponseEntity<?> getAllPlayersByTeam(
            @Parameter(description = Constants.pageable_description, example = Constants.pageable_request)
            @PageableDefault(size = 5) @SortDefault.SortDefaults({@SortDefault(sort = "playerId", direction = Sort.Direction.ASC)})
                    Pageable pageable,
            @Parameter(description = Constants.getLeagueById_pmt_description, required = true, example = "1")
            @PathVariable int teamId) {

        Page<PlayerResponse> players = playerService.getPlayersByTeam(pageable, teamService.getTeam(teamId));
        PagedModel<EntityModel<PlayerResponse>> collModel = playerResponsePagedResourcesAssembler.toModel(players, playerAssembler);
        return ResponseEntity.ok(collModel);
    }

    /**
     * @param playerId Identifier of the existing player
     * @param patch    Information of the player to be updated. For example, team ID, to assign the player to this team
     * @return RF5- Player updated (Useful to incorporate an existing player and other modifications)
     */
    @PatchMapping(path = URI_PLAYER + "/{playerId}", consumes = "application/json")
    @Operation(summary = Constants.partialPlayer_op_summary, description = Constants.partialPlayer_op_description,
            tags = "LeagueFreeAPI", responses =
    @ApiResponse(headers = @Header(name = "app-success", description = Constants.partialPlayer_head_description),
            responseCode = "204", description = Constants.partialPlayer_resp_description))
    @Throttling(timeFrameInSeconds = 60, calls = 3)
    public ResponseEntity<?> partialUpdatePlayerPartial(
            @Parameter(description = Constants.partialPlayer_pt1_description, required = true, example = "1")
            @PathVariable int playerId,
            @Parameter(description = Constants.partialPlayer_pt2_description, required = true)
            @RequestBody PlayerModal patch) {

        playerService.updatePlayerPartial(playerId, patch);

        String msg = contentHandler.successPartialUpdateAlert(ENTITY_PLAYER);

        return ResponseEntity.noContent()
                .headers(SuccessfulContentHandler.createHeaders(msg)).build();
    }

    /**
     * @param playerRequest PlayerRequest with the information of the player to be added
     * @return RF6- Player Added
     */
    @PostMapping(path = URI_PLAYER, consumes = "application/json")
    @Operation(summary = Constants.addPlayer_op_summary, description = Constants.addPlayer_op_description,
            tags = "LeagueFreeAPI", responses =
    @ApiResponse(headers = @Header(name = "app-success", description = Constants.addPlayer_head_description),
            content = @Content(schema = @Schema(implementation = PlayerResponse.class),
                    examples = {@ExampleObject(value = Constants.entityModel_player_byId1)}),
            links = {
                    @Link(name = "self", operationId = "getPlayerById", operationRef = "#/paths/~players~1{playerId}/get",
                            description = Constants.getPlayerById_link1_description,
                            parameters = @LinkParameter(name = "playerId", expression = "$request.path.playerId"))
            },
            responseCode = "201", description = Constants.addPlayer_resp_description))
    @Throttling(timeFrameInSeconds = 60, calls = 3)
    public ResponseEntity<?> addPlayer(
            @Parameter(description = Constants.addPlayer_ptm_description, required = true)
            @RequestBody PlayerRequest playerRequest) {

        PlayerResponse newPlayer = playerService.addPlayer(playerRequest);
        EntityModel<PlayerResponse> playerResponse = playerAssembler.toModel(newPlayer);

        String msg = contentHandler.successCreateAlert(ENTITY_PLAYER + " " + newPlayer.getPlayerName());

        return ResponseEntity
                .created(playerResponse.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .headers(SuccessfulContentHandler.createHeaders(msg))
                .body(playerResponse);
    }

    //-------------------------- OTHER ENDPOINTS WITH OTHER FUNCTIONALITIES --------------------------------////////////
    //------------------------------------  ENDPOINTS FROM LEAGUE --------------------------------------//////////////////
    @GetMapping(URI_LEAGUE + "/{leagueId}")
    @Operation(summary = Constants.getLeagueById_op_summary, description = Constants.getLeagueById_op_description,
            tags = "LeagueFreeAPI", responses =
    @ApiResponse(content = @Content(schema = @Schema(implementation = LeagueResponse.class),
            examples = {@ExampleObject(value = Constants.entityModel_league_byId1)}),
            links = {
                    @Link(name = "self", operationId = "getLeagueById", description = Constants.getLeagueById_link1_description,
                            parameters = @LinkParameter(name = "leagueId", expression = "$request.path.leagueId"))
            },
            responseCode = "200", description = Constants.getLeagueById_resp_description))
    @Throttling(timeFrameInSeconds = 60, calls = 3)
    public ResponseEntity<?> getLeagueById(
            @Parameter(description = Constants.getLeagueById_pmt_description, required = true, example = "1")
            @PathVariable int leagueId) {

        LeagueResponse leagueResponse = leagueService.getLeagueById(leagueId);
        return ResponseEntity.ok(leagueAssembler.toModel(leagueResponse));
    }

    @PostMapping(path = URI_LEAGUE, consumes = "application/json")
    @Operation(summary = Constants.addLeague_op_summary, description = Constants.addLeague_op_description,
            tags = "LeagueFreeAPI", responses =
    @ApiResponse(headers = @Header(name = "app-success", description = Constants.addLeague_head_description),
            content = @Content(schema = @Schema(implementation = LeagueResponse.class),
                    examples = {@ExampleObject(value = Constants.entityModel_league_byId1)}),
            links = {
                    @Link(name = "self", operationId = "getLeagueById", operationRef = "#/paths/~leagues~1{leagueId}/get",
                            description = Constants.getLeagueById_link1_description,
                            parameters = @LinkParameter(name = "leagueId", expression = "$request.path.leagueId"))
            },
            responseCode = "201", description = Constants.addLeague_resp_description))
    @Throttling(timeFrameInSeconds = 60, calls = 3)
    public ResponseEntity<?> addLeague(
            @Parameter(description = Constants.addLeague_ptm_description, required = true)
            @RequestBody LeagueRequest leagueRequest) {

        LeagueResponse newLeague = leagueService.addLeague(leagueRequest);
        EntityModel<LeagueResponse> leagueResponse = leagueAssembler.toModel(newLeague);

        String msg = contentHandler.successCreateAlert(ENTITY_LEAGUE + " " + newLeague.getLeagueName());

        return ResponseEntity
                .created(leagueResponse.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .headers(SuccessfulContentHandler.createHeaders(msg))
                .body(leagueResponse);
    }

    @PutMapping(path = URI_LEAGUE + "/{leagueId}", consumes = "application/json")
    @Operation(summary = Constants.updateLeague_op_summary, description = Constants.updateLeague_op_description,
            tags = "LeagueFreeAPI", responses =
    @ApiResponse(headers = @Header(name = "app-success", description = Constants.updateLeague_head_description),
            content = @Content(schema = @Schema(implementation = LeagueResponse.class),
                    examples = {@ExampleObject(value = Constants.entityModel_league_byId1)}),
            links = {
                    @Link(name = "self", operationId = "getLeagueById",
                            description = Constants.getLeagueById_link1_description,
                            parameters = @LinkParameter(name = "causeId", expression = "$request.path.causeId"))
            },
            responseCode = "200", description = Constants.updateLeague_resp_description))
    @Throttling(timeFrameInSeconds = 60, calls = 3)
    public ResponseEntity<?> updateLeague(
            @Parameter(description = Constants.updateLeague_pt1_description, required = true, example = "1")
            @PathVariable int leagueId,
            @Parameter(description = Constants.updateLeague_pt2_description, required = true)
            @RequestBody LeagueRequest leagueRequest) {

        LeagueResponse updateLeague = leagueService.updateLeague(leagueId, leagueRequest);
        EntityModel<LeagueResponse> leagueResponse = leagueAssembler.toModel(updateLeague);

        String msg = contentHandler.successUpdateAlert(ENTITY_LEAGUE + " " + updateLeague.getLeagueName());

        return ResponseEntity
                .created(leagueResponse.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .headers(SuccessfulContentHandler.createHeaders(msg))
                .body(leagueResponse);
    }

    @DeleteMapping(path = URI_LEAGUE + "/{leagueId}")
    @Operation(summary = Constants.deleteLeagueById_op_summary, description = Constants.deleteLeagueById_op_description,
            tags = "LeagueFreeAPI", responses =
    @ApiResponse(headers = @Header(name = "app-success", description = Constants.deleteLeagueById_head_description),
            responseCode = "204", description = Constants.deleteLeagueById_resp_description))
    @Throttling(timeFrameInSeconds = 60, calls = 3)
    public ResponseEntity<?> deleteLeagueById(
            @Parameter(description = Constants.deleteLeagueById_ptm_description, required = true, example = "1")
            @PathVariable int leagueId) {

        leagueService.deleteLeagueById(leagueId);
        String msg = contentHandler.successDeleteAlert(ENTITY_LEAGUE);

        return ResponseEntity.noContent()
                .headers(SuccessfulContentHandler.createHeaders(msg)).build();
    }

    //------------------------------------  ENDPOINTS FROM TEAM ----------------------------------------////////////////
    @GetMapping(URI_TEAM)
    @Operation(summary = Constants.getAllTeam_op_summary, description = Constants.getAllTeam_op_description,
            tags = "LeagueFreeAPI", responses =
    @ApiResponse(content = @Content(schema = @Schema(implementation = TeamResponse.class),
            examples = {@ExampleObject(value = Constants.pagedModel_team)}),
            links = {
                    @Link(name = "getTeamById", operationRef = "#/paths/~1teams~1{teamId}/get",
                            description = Constants.getTeamById_link1_description,
                            parameters = @LinkParameter(name = "teamId", expression = "$request.path.teamId")),
                    @Link(name = "self", operationId = "getAllTeams", description = Constants.getTeamById_link2_description,
                            parameters = {
                                    @LinkParameter(name = "pageable", expression = "$request.query.pageable")
                            })
            },
            responseCode = "200", description = Constants.getAllTeam_resp_description))
    @Throttling(timeFrameInSeconds = 60, calls = 3)
    public ResponseEntity<?> getAllTeams(
            @Parameter(description = Constants.pageable_description, example = Constants.pageable_request)
            @PageableDefault(size = 5) @SortDefault.SortDefaults({@SortDefault(sort = "teamId", direction = Sort.Direction.ASC)})
                    Pageable pageable) {

        Page<TeamResponse> teams = teamService.getAllTeams(pageable);
        PagedModel<EntityModel<TeamResponse>> collModel = teamResponsePagedResourcesAssembler.toModel(teams, teamAssembler);
        return ResponseEntity.ok(collModel);
    }

    @GetMapping(URI_TEAM + "/{teamId}")
    @Operation(summary = Constants.getTeamById_op_summary, description = Constants.getTeamById_op_description,
            tags = "LeagueFreeAPI", responses =
    @ApiResponse(content = @Content(schema = @Schema(implementation = TeamResponse.class),
            examples = {@ExampleObject(value = Constants.entityModel_team_byId1)}),
            links = {
                    @Link(name = "self", operationId = "getTeamById", description = Constants.getTeamById_link1_description,
                            parameters = @LinkParameter(name = "teamId", expression = "$request.path.teamId"))
            },
            responseCode = "200", description = Constants.getTeamById_resp_description))
    @Throttling(timeFrameInSeconds = 60, calls = 3)
    public ResponseEntity<?> getTeamById(
            @Parameter(description = Constants.getTeamById_pmt_description, required = true, example = "1")
            @PathVariable int teamId) {

        TeamResponse teamResponse = teamService.getTeamById(teamId);
        return ResponseEntity.ok(teamAssembler.toModel(teamResponse));
    }

    @PostMapping(path = URI_TEAM, consumes = "application/json")
    @Operation(summary = Constants.addTeam_op_summary, description = Constants.addTeam_op_description,
            tags = "TeamRestController", responses =
    @ApiResponse(headers = @Header(name = "app-success", description = Constants.addTeam_head_description),
            content = @Content(schema = @Schema(implementation = TeamResponse.class),
                    examples = {@ExampleObject(value = Constants.entityModel_team_byId1)}),
            links = {
                    @Link(name = "self", operationId = "getTeamById", operationRef = "#/paths/~teams~1{teamId}/get",
                            description = Constants.getTeamById_link1_description,
                            parameters = @LinkParameter(name = "teamId", expression = "$request.path.teamId"))
            },
            responseCode = "201", description = Constants.addTeam_resp_description))
    @Throttling(timeFrameInSeconds = 60, calls = 3)
    public ResponseEntity<?> addTeam(
            @Parameter(description = Constants.addTeam_ptm_description, required = true)
            @RequestBody TeamRequest teamRequest) {

        TeamResponse newTeam = teamService.addTeam(teamRequest);
        EntityModel<TeamResponse> teamResponse = teamAssembler.toModel(newTeam);

        String msg = contentHandler.successCreateAlert(ENTITY_TEAM + " " + newTeam.getTeamName());

        return ResponseEntity
                .created(teamResponse.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .headers(SuccessfulContentHandler.createHeaders(msg))
                .body(teamResponse);
    }

    @PutMapping(path = URI_TEAM + "/{teamId}", consumes = "application/json")
    @Operation(summary = Constants.updateTeam_op_summary, description = Constants.updateTeam_op_description,
            tags = "LeagueFreeAPI", responses =
    @ApiResponse(headers = @Header(name = "app-success", description = Constants.updateTeam_head_description),
            content = @Content(schema = @Schema(implementation = TeamResponse.class),
                    examples = {@ExampleObject(value = Constants.entityModel_team_byId1)}),
            links = {
                    @Link(name = "self", operationId = "getTeamById",
                            description = Constants.getTeamById_link1_description,
                            parameters = @LinkParameter(name = "causeId", expression = "$request.path.causeId"))
            },
            responseCode = "200", description = Constants.updateTeam_resp_description))
    @Throttling(timeFrameInSeconds = 60, calls = 3)
    public ResponseEntity<?> updateTeam(
            @Parameter(description = Constants.updateTeam_pt1_description, required = true, example = "1")
            @PathVariable int teamId,
            @Parameter(description = Constants.updateTeam_pt2_description, required = true)
            @RequestBody TeamRequest teamRequest) {

        TeamResponse updateTeam = teamService.updateTeam(teamId, teamRequest);
        EntityModel<TeamResponse> teamResponse = teamAssembler.toModel(updateTeam);

        String msg = contentHandler.successUpdateAlert(ENTITY_TEAM + " " + updateTeam.getTeamName());

        return ResponseEntity
                .created(teamResponse.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .headers(SuccessfulContentHandler.createHeaders(msg))
                .body(teamResponse);
    }

    @DeleteMapping(path = URI_TEAM + "/{teamId}")
    @Operation(summary = Constants.deleteTeamById_op_summary, description = Constants.deleteTeamById_op_description,
            tags = "LeagueFreeAPI", responses =
    @ApiResponse(headers = @Header(name = "app-success", description = Constants.deleteTeamById_head_description),
            responseCode = "204", description = Constants.deleteTeamById_resp_description))
    @Throttling(timeFrameInSeconds = 60, calls = 3)
    public ResponseEntity<?> deleteTeamById(
            @Parameter(description = Constants.deleteTeamById_ptm_description, required = true, example = "1")
            @PathVariable int teamId) {

        teamService.deleteTeamById(teamId);
        String msg = contentHandler.successDeleteAlert(ENTITY_TEAM);

        return ResponseEntity.noContent()
                .headers(SuccessfulContentHandler.createHeaders(msg)).build();
    }

    //------------------------------------  ENDPOINTS FROM PLAYERS -------------------------------------////////////////
    @GetMapping(URI_PLAYER)
    @Operation(summary = Constants.getAllPlayer_op_summary, description = Constants.getAllPlayer_op_description,
            tags = "LeagueFreeAPI", responses =
    @ApiResponse(content = @Content(schema = @Schema(implementation = PlayerResponse.class),
            examples = {@ExampleObject(value = Constants.pagedModel_player)}),
            links = {
                    @Link(name = "getPlayerById", operationRef = "#/paths/~1players~1{playerId}/get",
                            description = Constants.getPlayerById_link1_description,
                            parameters = @LinkParameter(name = "playerId", expression = "$request.path.playerId")),
                    @Link(name = "self", operationId = "getAllPlayers", description = Constants.getPlayerById_link2_description,
                            parameters = {
                                    @LinkParameter(name = "pageable", expression = "$request.query.pageable")
                            })
            },
            responseCode = "200", description = Constants.getAllPlayer_resp_description))
    @Throttling(timeFrameInSeconds = 60, calls = 3)
    public ResponseEntity<?> getAllPlayers(
            @Parameter(description = Constants.pageable_description, example = Constants.pageable_request)
            @PageableDefault(size = 5) @SortDefault.SortDefaults({@SortDefault(sort = "playerId", direction = Sort.Direction.ASC)})
                    Pageable pageable) {

        Page<PlayerResponse> players = playerService.getAllPlayers(pageable);
        PagedModel<EntityModel<PlayerResponse>> collModel = playerResponsePagedResourcesAssembler.toModel(players, playerAssembler);
        return ResponseEntity.ok(collModel);
    }

    @GetMapping(URI_PLAYER + "/{playerId}")
    @Operation(summary = Constants.getPlayerById_op_summary, description = Constants.getPlayerById_op_description,
            tags = "LeagueFreeAPI", responses =
    @ApiResponse(content = @Content(schema = @Schema(implementation = PlayerResponse.class),
            examples = {@ExampleObject(value = Constants.entityModel_player_byId1)}),
            links = {
                    @Link(name = "self", operationId = "getPlayerById", description = Constants.getPlayerById_link1_description,
                            parameters = @LinkParameter(name = "playerId", expression = "$request.path.playerId"))
            },
            responseCode = "200", description = Constants.getPlayerById_resp_description))
    @Throttling(timeFrameInSeconds = 60, calls = 3)
    public ResponseEntity<?> getPlayerById(
            @Parameter(description = Constants.getPlayerById_pmt_description, required = true, example = "1")
            @PathVariable int playerId) {

        PlayerResponse playerResponse = playerService.getPlayerById(playerId);
        return ResponseEntity.ok(playerAssembler.toModel(playerResponse));
    }

    @PutMapping(path = URI_PLAYER + "/{playerId}", consumes = "application/json")
    @Operation(summary = Constants.updatePlayer_op_summary, description = Constants.updatePlayer_op_description,
            tags = "LeagueFreeAPI", responses =
    @ApiResponse(headers = @Header(name = "app-success", description = Constants.updatePlayer_head_description),
            content = @Content(schema = @Schema(implementation = PlayerResponse.class),
                    examples = {@ExampleObject(value = Constants.entityModel_player_byId1)}),
            links = {
                    @Link(name = "self", operationId = "getPlayerById",
                            description = Constants.getPlayerById_link1_description,
                            parameters = @LinkParameter(name = "causeId", expression = "$request.path.causeId"))
            },
            responseCode = "200", description = Constants.updatePlayer_resp_description))
    @Throttling(timeFrameInSeconds = 60, calls = 3)
    public ResponseEntity<?> updatePlayer(
            @Parameter(description = Constants.updatePlayer_pt1_description, required = true, example = "1")
            @PathVariable int playerId,
            @Parameter(description = Constants.updatePlayer_pt2_description, required = true)
            @RequestBody PlayerRequest playerRequest) {

        PlayerResponse updatePlayer = playerService.updatePlayer(playerId, playerRequest);
        EntityModel<PlayerResponse> playerResponse = playerAssembler.toModel(updatePlayer);

        String msg = contentHandler.successUpdateAlert(ENTITY_PLAYER + " " + updatePlayer.getPlayerName());

        return ResponseEntity
                .created(playerResponse.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .headers(SuccessfulContentHandler.createHeaders(msg))
                .body(playerResponse);
    }

    @DeleteMapping(path = URI_PLAYER + "/{playerId}")
    @Operation(summary = Constants.deletePlayerById_op_summary, description = Constants.deletePlayerById_op_description,
            tags = "LeagueFreeAPI", responses =
    @ApiResponse(headers = @Header(name = "app-success", description = Constants.deletePlayerById_head_description),
            responseCode = "204", description = Constants.deletePlayerById_resp_description))
    @Throttling(timeFrameInSeconds = 60, calls = 3)
    public ResponseEntity<?> deletePlayerById(
            @Parameter(description = Constants.deletePlayerById_ptm_description, required = true, example = "1")
            @PathVariable int playerId) {

        playerService.deletePlayerById(playerId);
        String msg = contentHandler.successDeleteAlert(ENTITY_PLAYER);

        return ResponseEntity.noContent()
                .headers(SuccessfulContentHandler.createHeaders(msg)).build();
    }
}
