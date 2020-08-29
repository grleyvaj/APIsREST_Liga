package pe.avanzza.security.infraestructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.avanzza.security.domain.User;

import java.util.Optional;

/**
 * @author Gloria R. Leyva Jerez
 */
@Repository
public interface IUserJPARepository extends JpaRepository<User, Long> {

    User save(User user);

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}