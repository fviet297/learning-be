package com.learningapp.service;

import com.learningapp.dto.request.AuthenticationRequest;
import com.learningapp.dto.request.RegisterRequest;
import com.learningapp.dto.response.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse login(AuthenticationRequest request);
}
