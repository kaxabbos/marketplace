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

    @Secured({ADMIN, MANAGER, SELLER, USER})
    @GetMapping("/bind")
    public Result findAllByBind(@RequestParam String bind, @RequestParam String ownerId) {
        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success Find All By Bind",
                service.findAllByBind(bind, ownerId).stream().map(toDtoConverter::convert).collect(Collectors.toList())
        );
    }

    @Secured({MANAGER})
    @GetMapping("/{id}/active")
    public Result active(@PathVariable String id) {
        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success Active",
                toDtoConverter.convert(service.active(id))
        );
    }

    @Secured({MANAGER})
    @GetMapping("/{id}/refine")
    public Result refine(@PathVariable String id,@RequestParam String refine) {
        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success Refine",
                toDtoConverter.convert(service.refine(id,refine))
        );
    }

    @Secured({SELLER})
    @GetMapping("/{id}/waiting")
    public Result waiting(@PathVariable String id) {
        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success Refine",
                toDtoConverter.convert(service.waiting(id))
        );
    }

    @Secured({SELLER})
    @GetMapping("/{id}/archive")
    public Result archive(@PathVariable String id) {
        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success Refine",
                toDtoConverter.convert(service.archive(id))
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
    public Result update(@PathVariable String id, @Valid @RequestBody ProductDto productDto, @RequestParam String category) {
        Product update = toConverter.convert(productDto);
        Product updated = service.update(id, update, category);
        ProductDto updatedDto = toDtoConverter.convert(updated);
        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success Update",
                updatedDto
        );
    }

    @Secured({SELLER})
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
