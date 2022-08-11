package com.xjw.payment.service;

import com.xjw.payment.entity.RefundInfo;

/**
 * @Author xjw
 * @Date 2022/8/11 16:14
 * @Version 1.0
 */
public interface RefundInfoService {

    RefundInfo createRefundByOrderNoForAliPay(String orderNo, String reason);

    void updateRefundForAliPay(String refundNo, String content, String refundStatus);
}
