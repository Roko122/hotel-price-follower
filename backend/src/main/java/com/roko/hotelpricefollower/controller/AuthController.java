package com.roko.hotelpricefollower.controller;

import com.roko.hotelpricefollower.dto.AuthResponse;
import com.roko.hotelpricefollower.dto.LoginRequest;
import com.roko.hotelpricefollower.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    @Value("${jwt.expire-time}")
    private long expireTime;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        UserDetails userDetails = authenticationService.authenticate(
                loginRequest.username(),
                loginRequest.password()
        );
        String token = authenticationService.generateToken(userDetails);
        AuthResponse authResponse = new AuthResponse(token, expireTime);

        return ResponseEntity.ok(authResponse);
    }
}
