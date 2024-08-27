package com.marketplace.product.img.converter;

import com.marketplace.product.img.ProductImg;
import com.marketplace.product.img.ProductImgDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class ProductImgToProductImgDtoConverter implements Converter<ProductImg, ProductImgDto> {

    @Override
    public ProductImgDto convert(ProductImg source) {
        return new ProductImgDto(
                source.getId(),
                source.getImg()
        );
    }
}
