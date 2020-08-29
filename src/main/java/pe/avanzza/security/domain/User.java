package pe.avanzza.security.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Gloria R. Leyva Jerez
 */
@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    private static final long serialVersionUID = 8261525486575977493L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    private String name;

    @Column(unique = true, nullable = false, name = "username")
    private String username;

    private String email;

    private String password;

    private String rol;
}