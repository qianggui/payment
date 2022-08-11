package com.xjw.payment.task;


import com.xjw.payment.entity.OrderInfo;
import com.xjw.payment.enums.PayType;
import com.xjw.payment.service.AliPayService;
import com.xjw.payment.service.OrderInfoService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;


/**
 * @Author xjw
 * @Date 2022/8/7 11:08
 * @Version 1.0
 */
@Slf4j
@Component
public class AlipayTask {

    @Resource
    private OrderInfoService orderInfoService;

    @Resource
    private AliPayService aliPayService;

    /**
     * 从第0秒开始每隔30秒执行1次，查询创建超过5分钟，并且未支付的订单
     */
    @Scheduled(cron = "0/30 * * * * ?")
    public void orderConfirm(){

        log.info("orderConfirm 被执行......");

        List<OrderInfo> orderInfoList = orderInfoService.getNoPayOrderByDuration(1, PayType.ALIPAY.getType());

        for (OrderInfo orderInfo : orderInfoList) {
            String orderNo = orderInfo.getOrderNo();
            log.warn("超时订单 ===> {}", orderNo);

            //核实订单状态：调用支付宝查单接口
            aliPayService.checkOrderStatus(orderNo);
        }
    }
}
