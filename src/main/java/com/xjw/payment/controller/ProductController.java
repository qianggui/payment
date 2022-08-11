package com.xjw.payment.controller;


import com.xjw.payment.entity.Product;
import com.xjw.payment.service.ProductService;
import com.xjw.payment.vo.Res;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import java.util.List;


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

    @Resource
    private ProductService productService;
    @ApiOperation("商品列表")
    @GetMapping("/list")
    public Res list(){

        List<Product> list = productService.list();
        return Res.ok().data("productList", list);
    }

}
