package com.example.myJar;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

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
        if(authUserRepository.findByEmail(request.getEmail()).isPresent())
        {
            throw new RuntimeException("Email already exists");
        }
        String hashedPassword = passwordEncoder.encode(request.getPassword());
        AuthUsers user = new AuthUsers(request.getEmail(), hashedPassword, "Role_User");
        authUserRepository.save(user);
        return jwtService.generateToken(request.getEmail());
    }

    public String login(LoginDTO request)
    {
        AuthUsers user = authUserRepository.findByEmail(request.getEmail()).orElseThrow(()-> new ResourceNotFoundException("User not found"));


        if(!passwordEncoder.matches(request.getPassword(), user.getPassword()))
        {
            throw new RuntimeException("User not found");
        }
        return jwtService.generateToken(request.getEmail());
    }
}
