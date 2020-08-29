package pe.avanzza.security.infraestructure;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import pe.avanzza.security.domain.User;
import pe.avanzza.security.domain.IUserRepository;

import java.util.Optional;

/**
 * @author Gloria R. Leyva Jerez
 */
@Service
@Data
@AllArgsConstructor
public class UserRepositoryImpl implements IUserRepository {

    private final IUserJPARepository impl;

    @Override
    public User save(User user) {
        return impl.save(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return impl.findByUsername(username);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return impl.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return impl.existsByEmail(email);
    }
}
