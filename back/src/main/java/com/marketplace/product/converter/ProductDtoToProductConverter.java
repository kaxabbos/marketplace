package com.marketplace.product.converter;

import com.marketplace.product.Product;
import com.marketplace.product.ProductDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class ProductDtoToProductConverter implements Converter<ProductDto, Product> {
    @Override
    public Product convert(ProductDto source) {
        return new Product(
                source.name(),
                source.bind(),
                source.count(),
                source.price(),
                source.description()
        );
    }
}
