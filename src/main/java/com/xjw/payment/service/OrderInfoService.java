package com.xjw.payment.service;

import com.xjw.payment.entity.OrderInfo;
import com.xjw.payment.enums.OrderStatus;

import java.util.List;

/**
 * @Author xjw
 * @Date 2022/8/8 15:06
 * @Version 1.0
 */
public interface OrderInfoService {

    /**
     * 根据商品id创建订单
     * @param productId
     * @param paymentType
     * @return
     */
    OrderInfo createOrderByProductId(Long productId, String paymentType);

    /**
     * 根据订单号获取订单
     * @param orderNo
     * @return
     */
    OrderInfo getOrderByOrderNo(String orderNo);

    /**
     * 更新订单状态
     * @param orderNo
     * @param orderStatus
     */
    void updateStatusByOrderNo(String orderNo, OrderStatus orderStatus);

    /**
     * 得到订单状态
     * @param orderNo
     * @return
     */
    String getOrderStatus(String orderNo);

    /**
     * 查询创建超过minutes分钟并且未支付的订单
     * @param minutes
     * @param paymentType
     * @return
     */
    List<OrderInfo> getNoPayOrderByDuration(int minutes, String paymentType);

    /**
     * 订单列表
     * @return
     */
    List<OrderInfo> listOrderByCreateTimeDesc(int userId);



}
