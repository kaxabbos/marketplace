package com.marketplace.ordering;

import com.marketplace.ordering.converter.OrderingToOrderingDtoConverter;
import com.marketplace.system.Result;
import com.marketplace.system.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

import static com.marketplace.util.Global.SELLER;
import static com.marketplace.util.Global.USER;

@RestController
@RequestMapping("/orderings")
@RequiredArgsConstructor
public class OrderingController {

    private final OrderingService service;
    private final OrderingToOrderingDtoConverter toDtoConverter;

    @GetMapping
    @Secured({SELLER, USER})
    public Result findAll() {
        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success Find All",
                service.findAll().stream().map(toDtoConverter::convert).collect(Collectors.toList())
        );
    }

    @PostMapping
    @Secured({USER})
    public Result save(@RequestParam int count, @RequestParam String productId) {
        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success Save",
                toDtoConverter.convert(service.save(count, productId))
        );
    }

}
