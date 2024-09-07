package com.marketplace.ordering.converter;

import com.marketplace.ordering.Ordering;
import com.marketplace.ordering.OrderingDto;
import com.marketplace.product.converter.ProductToProductDtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderingToOrderingDtoConverter implements Converter<Ordering, OrderingDto> {

    private final ProductToProductDtoConverter productToProductDtoConverter;

    @Override
    public OrderingDto convert(Ordering source) {
        return new OrderingDto(
                source.getId(),
                source.getCount(),
                source.getPrice(),
                source.getTotal(),
                source.getStatus().name(),
                source.getStatus().getName(),
                source.getOwner().getId(),
                productToProductDtoConverter.convert(source.getProduct())
        );
    }
}
