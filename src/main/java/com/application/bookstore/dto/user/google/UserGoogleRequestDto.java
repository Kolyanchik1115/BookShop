package com.application.bookstore.dto.user.google;

import jakarta.validation.constraints.NotEmpty;

public record UserGoogleRequestDto(
        @NotEmpty(message = "token should be not empty")
        String googleToken
) {
}
