package com.example.myJar;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {


    public static final Logger logger = LoggerFactory.getLogger(AuthService.class);
    private final AuthUserRepository authUserRepository;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(AuthUserRepository authUserRepository, JwtService jwtService) {
        this.authUserRepository = authUserRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public String register(RegisterDTO request)
    {
        logger.info("Registering User with email:{}", request.getEmail());
        if(authUserRepository.findByEmail(request.getEmail()).isPresent())
        {
            logger.warn("Registration failed - Email already exists:{}", request.getEmail());
            throw new RuntimeException("Email already exists");
        }
        String hashedPassword = passwordEncoder.encode(request.getPassword());
        AuthUsers user = new AuthUsers(request.getEmail(), hashedPassword, "Role_User");
        authUserRepository.save(user);

        logger.info("User resgistered successfully:{}", request.getEmail());
        return jwtService.generateToken(request.getEmail());
    }

    public String login(LoginDTO request)
    {
        logger.info("Login attempt for the email:{}", request.getEmail());
        AuthUsers user = authUserRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> {
                    logger.error("Login failed — user not found: {}", request.getEmail());
                    return new ResourceNotFoundException("User not found");
                });
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword()))
        {
            logger.warn("Login attempt failed - Incorrect Password: {}",request.getPassword());
            throw new RuntimeException("User not found");
        }
        logger.info("Login successful for: {}", request.getEmail());
        return jwtService.generateToken(request.getEmail());
    }
}
