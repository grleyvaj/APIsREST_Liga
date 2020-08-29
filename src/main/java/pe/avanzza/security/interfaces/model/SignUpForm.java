package pe.avanzza.security.interfaces.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

/**
 * @author Gloria R. Leyva Jerez
 */
@Data
public class SignUpForm implements Serializable {

    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    @NotBlank(message = "{notBlank.username}")
    @Size(min = 3, max = 60, message = "{size.username}")
    private String username;

    @NotBlank
    @Size(max = 60)
    @Email
    private String email;

    private String rol;

    @NotBlank(message = "{notBlank.password}")
    @Size(min = 6, max = 40, message = "{size.password}")
    private String password;
}
