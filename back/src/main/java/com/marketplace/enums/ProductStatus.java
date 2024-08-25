package com.marketplace.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ProductStatus {
    WAITING("Ожидание"),
    ACTIVE("Ожидание"),
    REFINE("Доработать"),
    ARCHIVE("Ожидание"),
    ;

    private final String name;
}
