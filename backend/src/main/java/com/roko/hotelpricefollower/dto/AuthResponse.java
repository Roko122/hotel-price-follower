package com.roko.hotelpricefollower.dto;

public record AuthResponse(
        String token,
        long expiresIn
) {
}
