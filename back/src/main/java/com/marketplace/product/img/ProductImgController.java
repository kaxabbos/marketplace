package com.marketplace.product.img;

import com.marketplace.product.converter.ProductToProductDtoConverter;
import com.marketplace.system.Result;
import com.marketplace.system.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/products/{productId}/img")
@RequiredArgsConstructor
public class ProductImgController {

    private final ProductImgService service;
    private final ProductToProductDtoConverter toProductDtoConverter;

    @PostMapping
    public Result save(@PathVariable String productId, @RequestParam MultipartFile[] files) {
        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success Save",
                toProductDtoConverter.convert(service.save(files, productId))
        );
    }

    @DeleteMapping("/{imgId}")
    public Result delete(@PathVariable String productId, @PathVariable String imgId) {
        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success Delete",
                toProductDtoConverter.convert(service.delete(productId, imgId))
        );
    }

}
