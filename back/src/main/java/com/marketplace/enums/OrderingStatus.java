package com.marketplace.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderingStatus {
    WAITING("Ожидание"),

    ORDERED("Заказано"),

    DONE("Подтверждено"),
    REJECTED("Отказано"),

    DELIVERED("Доставлено"),

    ACCEPTED("Принято"),
    ;

    private final String name;
}
