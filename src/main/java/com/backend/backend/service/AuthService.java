package com.backend.backend.service;

import com.backend.backend.entity.User;
import com.backend.backend.entity.UserRole;
import com.backend.backend.repository.UserRepository;
import com.backend.backend.util.AuthResponse;
import com.backend.backend.util.LoginRequest;
import com.backend.backend.util.RegisterRequest;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;


@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final RefreshTokenService refreshTokenService;

    private final AuthenticationManager authenticationManager;


    public AuthResponse register(RegisterRequest registerRequest) {
        var user = User.builder()
                .name(registerRequest.getName())
                .email(registerRequest.getEmail())
                .username(registerRequest.getUsername())
                .gender(registerRequest.getGender())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(UserRole.USER)
                .build();

        User savedUser = userRepository.save(user);
        var accessToken = jwtService.generateToken(savedUser);
        var refreshToken = refreshTokenService.createRefreshToken(savedUser.getEmail());

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getRefreshToken())
                .build();
    }

    public AuthResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        var user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found!"));

        // Extract roles from the User entity
        Set<String> roles = Collections.singleton(user.getRole().name());


        var accessToken = jwtService.generateToken(user);
        var refreshToken = refreshTokenService.createRefreshToken(loginRequest.getEmail());

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getRefreshToken())
                .roles(roles)
                .build();
    }

}