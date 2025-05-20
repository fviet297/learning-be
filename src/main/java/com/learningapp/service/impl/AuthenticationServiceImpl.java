package com.learningapp.service.impl;

import com.learningapp.config.JwtService;
import com.learningapp.constants.CoreConstants;
import com.learningapp.dto.request.AuthenticationRequest;
import com.learningapp.dto.request.RegisterRequest;
import com.learningapp.dto.response.AuthenticationResponse;
import com.learningapp.entity.User;
import com.learningapp.enums.Role;
import com.learningapp.exception.BusinessException;
import com.learningapp.repository.UserRepository;
import com.learningapp.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        final String username = request.getUsername();
        if (repository.findByUsername(username).isPresent()) {
            throw new BusinessException(CoreConstants.MESSAGE_AUTH.USER_EXISTS);
        }
        final String password = passwordEncoder.encode(request.getPassword());
        final User user = new User();
        user.setPassword(password);
        user.setEmail(request.getEmail());
        user.setRole(Role.USER);
        user.setUsername(username);
        user.setFullName(request.getFullName());

        final String jwt = jwtService.generateToken(user);

        repository.save(user);
        return AuthenticationResponse.builder().token(jwt).userId(user.getId()).build();
    }

    @Override
    public AuthenticationResponse login(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
            final User user = repository.findByUsername(request.getUsername())
                    .orElseThrow();
            final String jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .userId(user.getId())
                    .build();
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(CoreConstants.MESSAGE_AUTH.INVALID_CREDENTIALS);
        }
    }
}
