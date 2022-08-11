package com.xjw.payment.controller;


import com.xjw.payment.entity.OrderInfo;
import com.xjw.payment.enums.OrderStatus;
import com.xjw.payment.service.OrderInfoService;
import com.xjw.payment.vo.Res;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author xjw
 * @Date 2022/8/11 16:30
 * @Version 1.0
 */
@CrossOrigin //开放前端的跨域访问
@Api(tags = "商品订单管理")
@RestController
@RequestMapping("/api/order-info")
public class OrderInfoController {
    @Resource
    private OrderInfoService orderInfoService;

    @ApiOperation("订单列表")
    @PostMapping("/list/{userId}")
//    @Po("/list/{userId}")
    public Res list(@PathVariable int userId){

        List<OrderInfo> list = orderInfoService.listOrderByCreateTimeDesc(userId);
        return Res.ok().data("list", list);
    }

}
