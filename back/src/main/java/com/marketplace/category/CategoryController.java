package com.marketplace.category;

import com.marketplace.category.converter.CategoryToCategoryDtoConverter;
import com.marketplace.system.Result;
import com.marketplace.system.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;
    private final CategoryToCategoryDtoConverter toDtoConverter;

    @GetMapping
    public Result findAll() {
        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success Find All",
                service.findAll().stream().map(toDtoConverter::convert).collect(Collectors.toList())
        );
    }
}
