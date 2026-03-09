package com.learningapp.controller;

import com.learningapp.dto.ResponseData;
import com.learningapp.dto.request.AuthenticationRequest;
import com.learningapp.dto.request.RegisterRequest;
import com.learningapp.dto.response.AuthenticationResponse;
import com.learningapp.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthController(final AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseData> register(
            @Valid @RequestBody RegisterRequest request
    ) {
        final AuthenticationResponse authenticationResponse = authenticationService.register(request);
        return ResponseEntity.ok(ResponseData.builder().data(authenticationResponse).build());
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseData> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        final AuthenticationResponse authenticationResponse = authenticationService.login(request);
        return ResponseEntity.ok(ResponseData.builder().data(authenticationResponse).build());
    }
}