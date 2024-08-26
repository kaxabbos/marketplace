package com.marketplace.category.converter;

import com.marketplace.category.Category;
import com.marketplace.category.CategoryDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class CategoryToCategoryDtoConverter implements Converter<Category, CategoryDto> {
    @Override
    public CategoryDto convert(Category source) {
        return new CategoryDto(
                source.getName()
        );
    }
}
