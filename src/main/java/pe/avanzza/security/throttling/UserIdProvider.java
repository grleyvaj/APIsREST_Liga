package pe.avanzza.security.throttling;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class UserIdProvider {
    public static Optional<String> getCurrentUserId() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                String currentUserName = authentication.getName();
                return Optional.of(currentUserName);
            }
        } catch (NullPointerException ex) {
            return Optional.of("");
        }

        return Optional.of("");
    }
}
