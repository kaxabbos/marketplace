package com.marketplace.ordering;

public record OrderingDto(
        Long id,
        int count,
        float price,
        float total,
        String status,
        String statusName,
        Long productId,
        Long ownerId
) {
}
