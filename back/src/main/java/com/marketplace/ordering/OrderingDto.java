package com.marketplace.ordering;

import com.marketplace.product.ProductDto;

public record OrderingDto(
        Long id,
        int count,
        float price,
        float total,
        String status,
        String statusName,
        Long ownerId,
        ProductDto product
) {
}
