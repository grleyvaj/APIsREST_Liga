package pe.avanzza.core.shared;

import org.springframework.stereotype.Component;

/**
 * @author Gloria R. Leyva Jerez
 * Class to define all api's uri
 */
@Component
public final class URIConstant {

    public static final String ENTITY_API = "/avanzza";
    public static final String API_FREE = "/v1";
    public static final String API_PAID = "/v2";

    public static final String URI_LEAGUE = "ligas";
    public static final String ENTITY_LEAGUE = "Ligas de fútbol";

    public static final String URI_TEAM = "equipos";
    public static final String ENTITY_TEAM = "Equipos";

    public static final String URI_PLAYER = "jugadores";
    public static final String ENTITY_PLAYER = "Jugadores";

    public static final String URI_USER = "usuarios";
    public static final String ENTITY_USER = "Usuarios";

}