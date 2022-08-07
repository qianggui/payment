package com.xjw.payment.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author xjw
 * @Date 2022/8/7 10:20
 * @Version 1.0
 */
@AllArgsConstructor
@Getter
public enum  PayType {
    /**
     * 未支付
     */
    NOTPAY("未支付"),


    /**
     * 支付成功
     */
    SUCCESS("支付成功"),

    /**
     * 已关闭
     */
    CLOSED("超时已关闭"),

    /**
     * 已取消
     */
    CANCEL("用户已取消"),

    /**
     * 退款中
     */
    REFUND_PROCESSING("退款中"),

    /**
     * 已退款
     */
    REFUND_SUCCESS("已退款"),

    /**
     * 退款异常
     */
    REFUND_ABNORMAL("退款异常");

    /**
     * 类型
     */
    private final String type;
}
