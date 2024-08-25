package com.marketplace.stats;

import com.marketplace.system.Result;
import com.marketplace.system.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.marketplace.util.Global.ADMIN;

@RestController
@RequestMapping("/stats")
@RequiredArgsConstructor
@Secured({ADMIN})
public class StatsController {


    @GetMapping("/categories")
    public Result getStatsCategories() {
        Map<String, List<?>> res = new HashMap<>();

        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success Stats Categories",
                Collections.unmodifiableMap(res)
        );
    }

}
