//package com.learningapp.controller;
//
//import com.learningapp.entity.UserEntity;
//import com.learningapp.repository.UserRepository;
//import com.learningapp.config.JwtService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final JwtService jwtService;
//    private final AuthenticationManager authenticationManager;
//
//    @Autowired
//    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder,
//                          JwtService jwtService, AuthenticationManager authenticationManager) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//        this.jwtService = jwtService;
//        this.authenticationManager = authenticationManager;
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<?> register(@RequestBody UserEntity userEntity) {
//        if (userRepository.findByUsername(userEntity.getUsername()).isPresent()) {
//            return ResponseEntity.badRequest().body("Username already exists");
//        }
//        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
//        userRepository.save(userEntity);
//        String jwt = jwtService.generateToken(
//                org.springframework.security.core.userdetails.User
//                        .withUsername(userEntity.getUsername())
//                        .password(userEntity.getPassword())
//                        .authorities("USER")
//                        .build()
//        );
//        return ResponseEntity.ok(new AuthResponse(jwt, userEntity.getId()));
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody UserEntity userEntity) {
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(userEntity.getUsername(), userEntity.getPassword())
//            );
//            UserEntity foundUserEntity = userRepository.findByUsername(userEntity.getUsername())
//                    .orElseThrow(() -> new RuntimeException("User not found"));
//            String jwt = jwtService.generateToken(
//                    org.springframework.security.core.userdetails.User
//                            .withUsername(foundUserEntity.getUsername())
//                            .password(foundUserEntity.getPassword())
//                            .authorities("USER")
//                            .build()
//            );
//            return ResponseEntity.ok(new AuthResponse(jwt, foundUserEntity.getId()));
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Invalid credentials");
//        }
//    }
//
//    private static class AuthResponse {
//        private final String token;
//        private final Long userId;
//
//        public AuthResponse(String token, Long userId) {
//            this.token = token;
//            this.userId = userId;
//        }
//
//        public String getToken() {
//            return token;
//        }
//
//        public Long getUserId() {
//            return userId;
//        }
//    }
//}