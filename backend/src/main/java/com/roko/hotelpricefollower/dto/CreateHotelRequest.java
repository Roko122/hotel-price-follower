package com.roko.hotelpricefollower.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

public record CreateHotelRequest(
        @NotBlank(message = "Hotel name can not be empty")
        @Size(min = 5, max = 50, message = "Hotel name should be 5-50 characters long")
        @Pattern(regexp = "^[a-zA-Z0-9 \\-']+$",
                message = "Hotel name can only contain letters, numbers, spaces, hyphens and apostrophes")
        String name,

        @URL
        @Pattern(
                regexp = "^https://img\\.tjareborg\\.fi/image/upload/[a-zA-Z0-9\\-._~:/?#\\[\\]@!$&'()*+,;%=]+$",
                message = "URL must start with https://img.tjareborg.fi/image/upload/ and contain only valid URL characters"
        )
        String imageUrl
) {
}
