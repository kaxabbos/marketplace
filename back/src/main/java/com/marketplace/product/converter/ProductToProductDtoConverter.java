package com.marketplace.product.converter;

import com.marketplace.product.Product;
import com.marketplace.product.ProductDto;
import com.marketplace.product.img.converter.ProductImgToProductImgDtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductToProductDtoConverter implements Converter<Product, ProductDto> {

    private final ProductImgToProductImgDtoConverter productImgToProductImgDtoConverter;

    @Override
    public ProductDto convert(Product source) {
        return new ProductDto(
                source.getId(),
                source.getName(),
                source.getBind(),
                source.getCount(),
                source.getPrice(),
                source.getDescription(),
                source.getStatus().name(),
                source.getStatus().getName(),
                source.getCategory().getName(),
                source.getOwner().getId(),
                source.getImg(),
                source.getImgs().stream().map(productImgToProductImgDtoConverter::convert).collect(Collectors.toList())
        );
    }
}
