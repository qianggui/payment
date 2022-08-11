package com.xjw.payment.service;

import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author xjw
 * @Date 2022/8/8 14:49
 * @Version 1.0
 */

public interface AliPayService {
    /**
     * 发起订单，订单的创建
     * @param productId
     * @return
     */
    String tradeCreate(Long productId);

    /**
     * 处理订单
     * @param params
     */
    void processOrder(Map<String, String> params);

    /**
     * 查询订单
     * @param orderNo
     * @return
     */
    String queryOrder(String orderNo);

    /**
     * 申请退款
     * @param orderNo
     * @param reason
     */

    void refund(String orderNo, String reason);

    /**
     * 查询退款
     * @param orderNo
     * @return
     */
    String queryRefund(String orderNo);

    /**
     * 根据账单类型和日期获取账单url地址
     * @param billDate
     * @param type
     * @return
     */
    String queryBill(String billDate, String type);

    /**
     * 取消订单
     * @param orderNo
     */
    void cancelOrder(String orderNo);

    /**
     * 核对订单的状态
     * @param orderNo
     */
    void checkOrderStatus(String orderNo);
}
