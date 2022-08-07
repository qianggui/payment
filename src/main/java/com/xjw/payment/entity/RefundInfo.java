package com.xjw.payment.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author xjw
 * @Date 2022/8/7 10:17
 * @Version 1.0
 */
@Data
@TableName("t_refund_info")
public class RefundInfo {

    private String orderNo;//商品订单编号

    private String refundNo;//退款单编号

    private String refundId;//支付系统退款单号

    private Integer totalFee;//原订单金额(分)

    private Integer refund;//退款金额(分)

    private String reason;//退款原因

    private String refundStatus;//退款单状态

    private String contentReturn;//申请退款返回参数

    private String contentNotify;//退款结果通知参数
}
