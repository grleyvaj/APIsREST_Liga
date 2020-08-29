package pe.avanzza.security.interfaces.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pe.avanzza.core.shared.URIConstant;
import pe.avanzza.security.application.UserDetailsServiceImpl;
import pe.avanzza.security.configuration.JwtProvider;
import pe.avanzza.security.domain.*;
import pe.avanzza.security.interfaces.model.JwtResponse;
import pe.avanzza.security.interfaces.model.LoginForm;
import pe.avanzza.security.interfaces.model.SignUpForm;

import javax.validation.Valid;

/**
 * @author Gloria R. Leyva Jerez
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = SecurityAPI.ENTITY_API, produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class SecurityAPI {

    protected static final String ENTITY_API = URIConstant.ENTITY_API;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    IUserRepository userRepository;

    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
//        userDetailsServiceImpl.userrole();
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<String>("Fail -> Username is already taken!",
                    HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<String>("Fail -> Email is already in use!",
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User();
        user.setName(signUpRequest.getName());
        user.setUsername(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(encoder.encode(signUpRequest.getPassword()));

        String strRoles = signUpRequest.getRol();

        switch (strRoles) {
            case "admin":
                user.setRol(RoleName.ROLE_ADMIN.name());
                break;
            case "free":
                user.setRol(RoleName.ROLE_FREE.name());
                break;
            default:
                user.setRol(RoleName.ROLE_PAID.name());
        }

        userRepository.save(user);

        return ResponseEntity.ok().body("User registered successfully!");
    }
}