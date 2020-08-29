package pe.avanzza.security.interfaces.model;

import lombok.Data;

/**
 * @author Gloria R. Leyva Jerez
 */
@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";

    public JwtResponse(String accessToken) {
        this.token = accessToken;
    }
}