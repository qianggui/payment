package com.xjw.payment.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author xjw
 * @Date 2022/8/7 10:22
 * @Version 1.0
 */
@AllArgsConstructor
@Getter
public enum OrderStatus {
    /**
     * 微信
     */
    WXPAY("微信"),


    /**
     * 支付宝
     */
    ALIPAY("支付宝");

    /**
     * 类型
     */
    private final String type;
}
