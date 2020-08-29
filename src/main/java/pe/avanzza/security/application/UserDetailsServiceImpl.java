package pe.avanzza.security.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.avanzza.core.exception.ResourceNotFoundException;
import pe.avanzza.core.shared.URIConstant;
import pe.avanzza.security.domain.User;
import pe.avanzza.security.domain.IUserRepository;

/**
 * @author Gloria R. Leyva Jerez
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final String ENTITY_USER = URIConstant.ENTITY_USER;

    @Autowired
    IUserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() ->
//                        new UsernameNotFoundException("User Not Found with -> username or email : " + username)
//                );

        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new ResourceNotFoundException(ENTITY_USER, "username", username)
                );

        return UserPrinciple.build(user);
    }
}