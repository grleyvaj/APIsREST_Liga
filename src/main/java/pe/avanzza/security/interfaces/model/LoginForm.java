package pe.avanzza.security.interfaces.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author Gloria R. Leyva Jerez
 */
@Data
public class LoginForm implements Serializable {

    private static final long serialVersionUID = 5784602255508721902L;

    @NotBlank(message = "{notBlank.username}")
    @Size(min = 3, max = 60, message = "{size.username}")
    private String username;

    @NotBlank(message = "{notBlank.password}")
    @Size(min = 6, max = 40, message = "{size.password}")
    private String password;
}