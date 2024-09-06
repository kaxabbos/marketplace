package com.marketplace.ordering.converter;

import com.marketplace.ordering.Ordering;
import com.marketplace.ordering.OrderingDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OrderingToOrderingDtoConverter implements Converter<Ordering, OrderingDto> {
    @Override
    public OrderingDto convert(Ordering source) {
        return new OrderingDto(
                source.getId(),
                source.getCount(),
                source.getPrice(),
                source.getTotal(),
                source.getStatus().name(),
                source.getStatus().getName(),
                source.getProduct().getId(),
                source.getOwner().getId()
        );
    }
}
