package pe.avanzza.league.interfaces.shared;

/**
 * @author Gloria R. Leyva Jerez
 * This class contains the OpenAPI documentation of the project's API
 */
public class Constants {

    /***********************
     * General constants
     **********************/
    public static final String id_description = "Identificador";
    public static final String id_example = "1";

    public static final String createdAt_description = "Fecha de creación";
    public static final String createdAt_example = "2020-06-14T16:45:40.683+0000";

    public static final String updatedAt_description = "Fecha de la última modificación";
    public static final String updatedAt_example = "2020-06-14T16:49:40.683+0000";

    public static final String pageable_request = "{"
            + "\"page\": 0,"
            + "\"size\": 5,"
            + "\"sort\": \"id,asc\""
            + "}";

    public static final String pageable_response =
            "\"page\": " + "{"
                    + "\"size\": 5,"
                    + "\"totalElements\": 15,"
                    + "\"totalPages\": 3,"
                    + "\"number\": 0"
                    + "}";

    public static final String pageable_description = "Parámetro para indicar la paginación";

    /***********************
     * LeagueRestController's Constants
     **********************/
    public static final String league_free_tag_name = "API REST FREE";
    public static final String league_paid_tag_name = "API REST PAID";
    public static final String league_free_tag_description = "Contiene las operaciones de la APIs REST Liga Versión FREE";
    public static final String league_paid_tag_description = "Contiene las operaciones de la APIs REST Liga Versión PAID";

    public static final String getAllLeague_op_summary = "RF1- Obtener las ligas";
    public static final String getAllLeague_op_description = "Obtener las ligas que se encuentran registradas según la paginación especificada.";
    public static final String getAllLeague_resp_description = "Devuelve una colección de Ligas con la información de las ligas que se encuentran registradas y sus links correspondientes de forma satisfactoria.";

    public static final String getLeagueById_op_summary = "Obtener liga";
    public static final String getLeagueById_op_description = "Obtener una liga por su identificador.";

    public static final String getAllTeamsByLeague_op_summary = "RF2- Listado de equipos que pertenecen a una liga";
    public static final String getAllTeamsByLeague_op_description = "Obtener el listado de equipos que pertenecen a la liga especificada y según la paginación establecida.";
    public static final String getAllTeamsByLeague_resp_description = "Devuelve una colección de equipos que corresponde a la liga y sus links correspondientes de forma satisfactoria.";

    public static final String getAllLeagueByTeam_op_summary = "RF3- Listado de ligas en las que jugará un equipo";
    public static final String getAllLeagueByTeam_op_description = "Obtener el listado de ligas en las que jugará el equipo especificado y según la paginación establecida.";
    public static final String getAllLeagueByTeam_resp_description = "Devuelve una colección de ligas que corresponde al equipo especificado y sus links correspondientes de forma satisfactoria.";

    public static final String entity_league_response = "\"id\": 1,"
            + "\"name\": \"Liga\","
            + "\"startDate\": \"2020-06-25\","
            + "\"end_date\": \"2020-09-25\","
            + "\"gameNumber\": \"38\","
            + "\"createdAt\": \"2018-06-14 12:41:03\","
            + "\"updatedAt\": \"2020-06-14 15:13:29\",";

    public static final String links_one_league_response = "\"_links\": {"
            + "\"self\": {" + "\"href\": \"http://127.0.0.1:8082/avanzza/v1/ligas/1\"" + "}" + "}";

    public static final String entityModel_league_byId1 = "{"
            + entity_league_response
            + links_one_league_response
            + "}";

    public static final String links_all__league_response = "\"_links\": {"
            + "\"first\": {" + "\"href\": \"http://127.0.0.1:8082/avanzza/v1/ligas?page=0&size=5&sort=id,asc\"" + "},"
            + "\"self\": {" + "\"href\": \"http://127.0.0.1:8082/avanzza/v1/ligas?page=0&size=5&sort=id,asc\"" + "},"
            + "\"next\": {" + "\"href\": \"http://127.0.0.1:8082/avanzza/v1/ligas?page=1&size=5&sort=id,asc\"" + "},"
            + "\"last\": {" + "\"href\": \"http://127.0.0.1:8082/avanzza/v1/ligas?page=2&size=5&sort=id,asc\"" + "}"
            + "},";

    public static final String pagedModel_league = "{"
            + "\"_embedded\": " + "{"
            + "\"leagueResponseList\": " +
            "[" + entityModel_league_byId1 + "]"
            + "}" + ","
            + links_all__league_response
            + pageable_response
            + "}";

    public static final String getLeagueById_link1_description = "Liga por su identificador";
    public static final String getLeagueById_link2_description = "Listado de ligas";
    public static final String getLeagueById_resp_description = "Devuelve un recurso LeagueResponse con la información de la liga correspondiente al identificador especificado y sus links correspondientes de forma satisfactoria.";
    public static final String getLeagueById_pmt_description = "Parámetro que indica el identificador de la liga a obtener.";

    public static final String getAllPlayersByTeam_op_summary = "RF4- Obtener jugadores por el identificador del equipo";
    public static final String getAllPlayersByTeam_op_description = "Obtener todos los jugadores que se encuentran registrados por el identificador del equipo";
    public static final String getAllPlayersByTeam__resp_description = "Devuelve una colección de jugadores pertenecientes al equipo especificador que se encuentran registrados y sus links correspondientes de forma satisfactoria.";

    public static final String addLeague_op_summary = "Adicionar una liga";
    public static final String addLeague_op_description = "Adicionar una liga con la información proporcionada en LeagueRequest.";
    public static final String addLeague_head_description = "Liga registrada satisfactoriamente";
    public static final String addLeague_resp_description = "Devuelve un recurso LeagueResponse con la información de la liga creada y sus links correspondientes de forma satisfactoria.";
    public static final String addLeague_ptm_description = "Parámetro que indica la LeagueRequest con la información de la liga que se desea adicionar.";

    public static final String updateLeague_op_summary = "Actualizar liga";
    public static final String updateLeague_op_description = "Actualizar la liga correspondiente al identificador especificado con la información proporcionada en LeagueRequest.";
    public static final String updateLeague_head_description = "Liga actualizada satisfactoriamente";
    public static final String updateLeague_resp_description = "Devuelve un recurso LeagueResponse con la información actualizada y sus links correspondientes de forma satisfactoria.";
    public static final String updateLeague_pt1_description = "Parámetro que indica el identificador de la liga que desea actualizar.";
    public static final String updateLeague_pt2_description = "Parámetro que indica  la LeagueRequest con la información de la liga que desea actualizar.";

    public static final String deleteLeagueById_op_summary = "Eliminar liga";
    public static final String deleteLeagueById_op_description = "Eliminar la liga correspondiente al identificador especificado.";
    public static final String deleteLeagueById_head_description = "Liga eliminada satisfactoriamente";
    public static final String deleteLeagueById_resp_description = "Elimina satisfactoriamente la liga y no devuelve contenido.";
    public static final String deleteLeagueById_ptm_description = "Parámetro que indica el identificador de la liga que desea eliminar.";

    /********************************************************************
     * LeagueRequest's Constants and LeagueResponse's Constants
     ********************************************************************/
    public static final String leagueRq_schema_description = "Recurso LeagueRequest con la información correspondiente a la liga";
    public static final String leagueRp_schema_description = "Recurso LeagueResponse que devuelve la información correspondiente la liga";

    public static final String league_name_description = "Nombre de la liga";
    public static final String league_name_example = "Española";

    public static final String league_startDate_description = "Fecha de inicio de la liga";
    public static final String league_startDate_example = "2020-09-01";

    public static final String league_endDate_description = "Fecha de inicio de la liga";
    public static final String league_endDate_example = "2020-12-01";

    public static final String league_gameNumber_description = "Cantidad de fechas a jugarse";
    public static final String league_gameNumber_example = "34";

    /***********************
     * TeamRestController's Constants
     **********************/
    public static final String team_tag_name = "Controlador REST de Equipo";
    public static final String team_tag_description = "Contiene las operaciones realizadas sobre la entidad Equipo";

    public static final String getAllTeam_op_summary = "Obtener los equipos";
    public static final String getAllTeam_op_description = "Obtener los equipos que se encuentran registrados según la paginación especificada.";
    public static final String getAllTeam_resp_description = "Devuelve una colección de equipos con la información de los equipos que se encuentran registrados y sus links correspondientes de forma satisfactoria.";

    public static final String getTeamById_op_summary = "Obtener equipo";
    public static final String getTeamById_op_description = "Obtener un equipo por su identificador.";

    public static final String entity_team_response = "\"id\": 1,"
            + "\"name\": \"Liga\","
            + "\"passNumber\": \"38\","
            + "\"faultNumber\": \"38\","
            + "\"leagueId\": \"1\","
            + "\"createdAt\": \"2018-06-14 12:41:03\","
            + "\"updatedAt\": \"2020-06-14 15:13:29\",";

    public static final String links_one_team_response = "\"_links\": {"
            + "\"self\": {" + "\"href\": \"http://127.0.0.1:8082/avanzza/v1/equipos/1\"" + "}" + "}";

    public static final String entityModel_team_byId1 = "{"
            + entity_team_response
            + links_one_team_response
            + "}";

    public static final String links_all__team_response = "\"_links\": {"
            + "\"first\": {" + "\"href\": \"http://127.0.0.1:8082/avanzza/v1/equipos?page=0&size=5&sort=id,asc\"" + "},"
            + "\"self\": {" + "\"href\": \"http://127.0.0.1:8082/avanzza/v1/equipos?page=0&size=5&sort=id,asc\"" + "},"
            + "\"next\": {" + "\"href\": \"http://127.0.0.1:8082/avanzza/v1/equipos?page=1&size=5&sort=id,asc\"" + "},"
            + "\"last\": {" + "\"href\": \"http://127.0.0.1:8082/avanzza/v1/equipos?page=2&size=5&sort=id,asc\"" + "}"
            + "},";

    public static final String pagedModel_team = "{"
            + "\"_embedded\": " + "{"
            + "\"teamResponseList\": " +
            "[" + entityModel_team_byId1 + "]"
            + "}" + ","
            + links_all__team_response
            + pageable_response
            + "}";

    public static final String getTeamById_link1_description = "Equipo por su identificador";
    public static final String getTeamById_link2_description = "Listado de equipos";
    public static final String getTeamById_resp_description = "Devuelve un recurso TeamResponse con la información del equipo correspondiente al identificador especificado y sus links correspondientes de forma satisfactoria.";
    public static final String getTeamById_pmt_description = "Parámetro que indica el identificador del equipo a obtener.";

    public static final String addTeam_op_summary = "Adicionar un equipo";
    public static final String addTeam_op_description = "Adicionar un equipo con la información proporcionada en TeamRequest.";
    public static final String addTeam_head_description = "Equipo registrado satisfactoriamente";
    public static final String addTeam_resp_description = "Devuelve un recurso TeamResponse con la información del equipo creado y sus links correspondientes de forma satisfactoria.";
    public static final String addTeam_ptm_description = "Parámetro que indica la TeamRequest con la información del equipo que se desea adicionar.";

    public static final String updateTeam_op_summary = "Actualizar equipo";
    public static final String updateTeam_op_description = "Actualizar el equipo correspondiente al identificador especificado con la información proporcionada en TeamRequest.";
    public static final String updateTeam_head_description = "Equipo actualizado satisfactoriamente";
    public static final String updateTeam_resp_description = "Devuelve un recurso TeamResponse con la información actualizada y sus links correspondientes de forma satisfactoria.";
    public static final String updateTeam_pt1_description = "Parámetro que indica el identificador del equipo que desea actualizar.";
    public static final String updateTeam_pt2_description = "Parámetro que indica el TeamRequest con la información del equipo que desea actualizar.";

    public static final String deleteTeamById_op_summary = "Eliminar equipo";
    public static final String deleteTeamById_op_description = "Eliminar el equipo correspondiente al identificador especificado.";
    public static final String deleteTeamById_head_description = "Equipo eliminado satisfactoriamente";
    public static final String deleteTeamById_resp_description = "Elimino satisfactoriamente el equipo y no devuelve contenido.";
    public static final String deleteTeamById_ptm_description = "Parámetro que indica el identificador del equipo que desea eliminar.";

    /********************************************************************
     * TeamRequest's Constants and TeamResponse's Constants
     ********************************************************************/
    public static final String teamRq_schema_description = "Recurso TeamRequest con la información correspondiente al equipo";
    public static final String teamRp_schema_description = "Recurso TeamResponse que devuelve la información correspondiente el equipo";

    public static final String team_name_description = "Nombre del equipo";
    public static final String team_name_example = "FC-Barcelona";

    public static final String team_passNumber_description = "Cantidad de pases";
    public static final String team_passNumber_example = "5200";

    public static final String team_faultNumber_description = "Cantidad de pases fallados";
    public static final String team_faultNumber_example = "500";

    public static final String team_leagueId_description = "Identificador de la liga a la que pertenece el equipo";
    public static final String team_leagueId_example = "1";

    /***********************
     * PlayerRestController's Constants
     **********************/
    public static final String player_tag_name = "Controlador REST de Jugador";
    public static final String player_tag_description = "Contiene las operaciones realizadas sobre la entidad Jugador";

    public static final String getAllPlayer_op_summary = "Obtener los jugadores";
    public static final String getAllPlayer_op_description = "Obtener los jugadores que se encuentran registrados según la paginación especificada.";
    public static final String getAllPlayer_resp_description = "Devuelve una colección de jugadores con la información de los jugadores que se encuentran registrados y sus links correspondientes de forma satisfactoria.";

    public static final String getPlayerById_op_summary = "Obtener jugador";
    public static final String getPlayerById_op_description = "Obtener un jugador por su identificador.";

    public static final String entity_player_response = "\"id\": 1,"
            + "\"name\": \"Liga\","
            + "\"nationality\": \"Perú\","
            + "\"height\": \"32.5\","
            + "\"rightHanded\": \"32.5\","
            + "\"teamId\": \"3\","
            + "\"createdAt\": \"2018-06-14 12:41:03\","
            + "\"updatedAt\": \"2020-06-14 15:13:29\",";

    public static final String links_one_player_response = "\"_links\": {"
            + "\"self\": {" + "\"href\": \"http://127.0.0.1:8082/avanzza/v1/jugadores/1\"" + "}" + "}";

    public static final String entityModel_player_byId1 = "{"
            + entity_player_response
            + links_one_player_response
            + "}";

    public static final String links_all_player_response = "\"_links\": {"
            + "\"first\": {" + "\"href\": \"http://127.0.0.1:8082/avanzza/v1/jugadores?page=0&size=5&sort=id,asc\"" + "},"
            + "\"self\": {" + "\"href\": \"http://127.0.0.1:8082/avanzza/v1/jugadores?page=0&size=5&sort=id,asc\"" + "},"
            + "\"next\": {" + "\"href\": \"http://127.0.0.1:8082/avanzza/v1/jugadores?page=1&size=5&sort=id,asc\"" + "},"
            + "\"last\": {" + "\"href\": \"http://127.0.0.1:8082/avanzza/v1/jugadores?page=2&size=5&sort=id,asc\"" + "}"
            + "},";

    public static final String pagedModel_player = "{"
            + "\"_embedded\": " + "{"
            + "\"playerResponseList\": " +
            "[" + entityModel_player_byId1 + "]"
            + "}" + ","
            + links_all_player_response
            + pageable_response
            + "}";

    public static final String getPlayerById_link1_description = "Jugador por su identificador";
    public static final String getPlayerById_link2_description = "Listado de jugadores";
    public static final String getPlayerById_resp_description = "Devuelve un recurso PlayerResponse con la información del jugador correspondiente al identificador especificado y sus links correspondientes de forma satisfactoria.";
    public static final String getPlayerById_pmt_description = "Parámetro que indica el identificador del jugador que se desea obtener.";

    public static final String addPlayer_op_summary = "RF6- Adicionar un jugador";
    public static final String addPlayer_op_description = "Adicionar un jugador con la información proporcionada en PlayerRequest.";
    public static final String addPlayer_head_description = "Jugador registrado satisfactoriamente";
    public static final String addPlayer_resp_description = "Devuelve un recurso PlayerResponse con la información del jugador creado y sus links correspondientes de forma satisfactoria.";
    public static final String addPlayer_ptm_description = "Parámetro que indica la PlayerRequest con la información del jugador que se desea adicionar.";

    public static final String updatePlayer_op_summary = "Actualizar los datos del jugador";
    public static final String updatePlayer_op_description = "Actualizar el jugador correspondiente al identificador especificado con la información proporcionada en PlayerRequest.";
    public static final String updatePlayer_head_description = "Jugador actualizado satisfactoriamente";
    public static final String updatePlayer_resp_description = "Devuelve un recurso PlayerResponse con la información actualizada y sus links correspondientes de forma satisfactoria.";
    public static final String updatePlayer_pt1_description = "Parámetro que indica el identificador del jugador que desea actualizar.";
    public static final String updatePlayer_pt2_description = "Parámetro que indica  la PlayerRequest con la información del jugador que desea actualizar.";

    public static final String partialPlayer_op_summary = "RF5 - Actualizar parcial de un Jugador (Asignarle equipo)";
    public static final String partialPlayer_op_description = "Actualizar parcialmente un jugador correspondiente al identificador especificado con la información proporcionada en PlayerModal. Ejemplo: Actualizar el equipo al que pertenece";
    public static final String partialPlayer_head_description = "Jugador actualizado satisfactoriamente";
    public static final String partialPlayer_resp_description = "Actualizo satisfactoriamente el jugador y no devuelve contenido.";
    public static final String partialPlayer_pt1_description = "Parámetro que indica el identificador del jugador que desea actualizar.";
    public static final String partialPlayer_pt2_description = "Tabla que contiene los campos y valores que se desean actualizar del jugador";


    public static final String deletePlayerById_op_summary = "Eliminar jugador";
    public static final String deletePlayerById_op_description = "Eliminar el jugador correspondiente al identificador especificado.";
    public static final String deletePlayerById_head_description = "Jugador eliminado satisfactoriamente";
    public static final String deletePlayerById_resp_description = "Elimina satisfactoriamente al jugador y no devuelve contenido.";
    public static final String deletePlayerById_ptm_description = "Parámetro que indica el identificador del jugador que desea eliminar.";

    /********************************************************************
     * PlayerRequest's Constants and PlayerResponse's Constants
     ********************************************************************/
    public static final String playerRq_schema_description = "Recurso PlayerRequest con la información correspondiente al jugador";
    public static final String playerRp_schema_description = "Recurso PlayerResponse que devuelve la información correspondiente al jugador";
    public static final String playerModalRq_schema_description = "Recurso PlayerModal con solamente la información del jugador que se desea actualizar";

    public static final String player_name_description = "Nombre del jugador";
    public static final String player_name_example = "Messi";

    public static final String player_nationality_description = "Nacionalidad";
    public static final String player_nationality_example = "Argentina";

    public static final String player_height_description = "Estatura";
    public static final String player_height_example = "1.70";

    public static final String player_rightHanded_description = "Diestro";
    public static final String player_rightHanded_example = "False";

    public static final String player_teamId_description = "Identificador del equipo en el que juega";
    public static final String player_teamId_example = "False";

}
