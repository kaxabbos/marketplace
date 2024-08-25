package com.marketplace.util;

import com.marketplace.system.Result;
import com.marketplace.system.StatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/")
    public Result Main() {
        return new Result(
                true,
                StatusCode.SUCCESS,
                "Server is up!"
        );
    }

}
