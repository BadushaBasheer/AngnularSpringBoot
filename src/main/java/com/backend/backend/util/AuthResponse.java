package com.backend.backend.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponse {

    private String accessToken;

    private String refreshToken;

    private Set<String> roles;

}