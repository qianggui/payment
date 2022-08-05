package com.xjw.payment.controller;

import com.xjw.payment.vo.Res;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

/**
 * @Author xjw
 * @Date 2022/8/5 14:18
 * @Version 1.0
 */

@CrossOrigin //开放前端的跨域访问
@Api(tags = "商品管理")
@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Operation(summary = "提交订单")
    @PostMapping("/order")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", dataTypeClass = Long.class, paramType = "query", example = "123"),
            @ApiImplicitParam(name = "goodsId", value = "商品id", dataTypeClass = Integer.class, paramType = "query", example = "1")
    })
    public Res toBuy(@ApiIgnore @RequestParam Map<String, String> params) {
        System.out.println(params);
        return Res.ok();
    }
}
