package com.marketplace.product.converter;

import com.marketplace.product.Product;
import com.marketplace.product.ProductDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class ProductToProductDtoConverter implements Converter<Product, ProductDto> {
    @Override
    public ProductDto convert(Product source) {
        return new ProductDto(
                source.getId(),
                source.getName(),
                source.getBind(),
                source.getCount(),
                source.getPrice(),
                source.getDescription(),
                source.getCategory().getName(),
                source.getOwner().getId(),
                source.getImgsString()
        );
    }
}
