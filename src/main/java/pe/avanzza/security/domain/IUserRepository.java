package pe.avanzza.security.domain;

import java.util.Optional;

/**
 * @author Gloria R. Leyva Jerez
 */
public interface IUserRepository {

    User save(User user);

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
