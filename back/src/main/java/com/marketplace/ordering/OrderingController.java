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

    @PatchMapping("/{id}")
    @Secured({USER})
    public Result update(@RequestParam int count, @PathVariable String id) {
        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success Update",
                toDtoConverter.convert(service.update(id, count))
        );
    }

    @GetMapping("/{id}/ordered")
    @Secured({USER})
    public Result ordered(@PathVariable String id) {
        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success Ordered",
                toDtoConverter.convert(service.ordered(id))
        );
    }

    @GetMapping("/{id}/done")
    @Secured({SELLER})
    public Result done(@PathVariable String id) {
        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success Done",
                toDtoConverter.convert(service.done(id))
        );
    }

    @GetMapping("/{id}/rejected")
    @Secured({SELLER})
    public Result rejected(@PathVariable String id) {
        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success Rejected",
                toDtoConverter.convert(service.rejected(id))
        );
    }

    @GetMapping("/{id}/delivered")
    @Secured({SELLER})
    public Result delivered(@PathVariable String id) {
        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success Delivered",
                toDtoConverter.convert(service.delivered(id))
        );
    }

    @GetMapping("/{id}/accepted")
    @Secured({USER})
    public Result accepted(@PathVariable String id) {
        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success Accepted",
                toDtoConverter.convert(service.accepted(id))
        );
    }

}
