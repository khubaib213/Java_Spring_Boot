package com.example.myJar;


import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    public AuthController(AuthService authService)
    {
        this.authService=authService;
    }
    @PostMapping("/register")
    public ResponseEntity<?> Register(@Valid @RequestBody RegisterDTO request)
    {
        String token = authService.register(request);
        return ResponseEntity.ok(Map.of("token", token));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDTO request)
    {
        String token = authService.login(request);
        return ResponseEntity.ok(Map.of("token",token));
    }

}
