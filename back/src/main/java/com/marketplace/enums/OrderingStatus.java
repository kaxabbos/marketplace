package com.marketplace.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderingStatus {
    WAITING("Ожидание"),
    ORDERED("Заказано"),
    DONE("Подтверждено"),
    DELIVERED("Доставлено"),
    ACCEPTED("Принято"),
    REJECTED("Отказано"),
    ;

    private final String name;
}
