package com.marketplace.product;

import com.marketplace.product.converter.ProductDtoToProductConverter;
import com.marketplace.product.converter.ProductToProductDtoConverter;
import com.marketplace.system.Result;
import com.marketplace.system.StatusCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

import static com.marketplace.util.Global.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;
    private final ProductToProductDtoConverter toDtoConverter;
    private final ProductDtoToProductConverter toConverter;

    @GetMapping
    public Result findAll() {
        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success Find All",
                service.findAll().stream().map(toDtoConverter::convert).collect(Collectors.toList())
        );
    }

    @Secured({ADMIN, MANAGER, SELLER, USER})
    @GetMapping("/{id}")
    public Result findById(@PathVariable String id) {
        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success Find By Id",
                toDtoConverter.convert(service.findById(id))
        );
    }

    @Secured({SELLER})
    @PostMapping
    public Result save(@Valid @RequestBody ProductDto productDto, @RequestParam String category) {
        Product save = toConverter.convert(productDto);
        Product saved = service.save(save, category);
        ProductDto savedDto = toDtoConverter.convert(saved);
        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success Save",
                savedDto
        );
    }

    @Secured({SELLER})
    @PutMapping("/{id}")
    public Result updateById(@PathVariable String id, @Valid @RequestBody ProductDto productDto, @RequestParam String category) {
        Product update = toConverter.convert(productDto);
        Product updated = service.updateById(id, update, category);
        ProductDto updatedDto = toDtoConverter.convert(updated);
        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success Update",
                updatedDto
        );
    }

    @Secured({ADMIN, MANAGER, SELLER})
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable String id) {
        service.deleteById(id);
        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success Delete"
        );
    }


}
