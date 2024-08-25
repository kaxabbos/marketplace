package com.marketplace.product;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public record ProductDto(
        Long id,

        @NotEmpty(message = "name is required")
        @Size(min = 1, max = 255, message = "name length = 1-255")
        String name,

        @NotEmpty(message = "bind is required")
        @Size(min = 1, max = 255, message = "name length = 1-255")
        String bind,

        @Min(value = 0, message = "count min = 0")
        @Max(value = 1000, message = "count max = 1000")
        int count,

        @Min(value = 1, message = "price min = 0.01")
        @Max(value = 1000, message = "price max = 1000")
        float price,

        @NotEmpty(message = "description is required")
        @Size(min = 1, max = 5000, message = "category length = 1-5000")
        String description,

        String category,
        Long ownerId,
        List<String> imgs
) {
}
