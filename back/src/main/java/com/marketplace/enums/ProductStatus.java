package com.marketplace.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ProductStatus {
    WAITING("Ожидание"),
    ACTIVE("Активно"),
    REFINE("Доработать"),
    ARCHIVE("Архив"),
    ;

    private final String name;
}
