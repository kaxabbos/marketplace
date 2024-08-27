package com.marketplace.product;

import com.marketplace.product.img.ProductImgDto;
import jakarta.validation.constraints.*;

import java.util.List;

public record ProductDto(
        Long id,

        @NotEmpty(message = "name is required")
        @Size(min = 1, max = 255, message = "name length = 1-255")
        String name,

        @NotEmpty(message = "bind is required")
        @Size(min = 1, max = 255, message = "name length = 1-255")
        String bind,

        @NotNull(message = "count is required")
        @Min(value = 0, message = "count min = 0")
        @Max(value = 1000, message = "count max = 1000")
        Integer count,

        @NotNull(message = "price is required")
        @Min(value = 0, message = "price min = 0")
        @Max(value = 1000, message = "price max = 1000")
        Float price,

        @NotEmpty(message = "description is required")
        @Size(min = 1, max = 5000, message = "category length = 1-5000")
        String description,

        String status,
        String statusName,

        String category,
        String refine,

        Long ownerId,

        String img,
        List<ProductImgDto> imgs
) {
}
