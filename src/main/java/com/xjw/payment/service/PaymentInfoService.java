package com.xjw.payment.service;

import java.util.Map;

/**
 * @Author xjw
 * @Date 2022/8/9 10:46
 * @Version 1.0
 */
public interface PaymentInfoService {
    /**
     * 创建支付记录
     * @param params
     */
    void createPaymentInfoForAliPay(Map<String, String> params);
}
