package com.xjw.payment.service.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.xjw.payment.entity.PaymentInfo;
import com.xjw.payment.enums.PayType;
import com.xjw.payment.mapper.PaymentInfoMapper;
import com.xjw.payment.service.PaymentInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author xjw
 * @Date 2022/8/9 10:46
 * @Version 1.0
 */
@Service
@Slf4j
public class PaymentInfoServiceImp extends ServiceImpl<PaymentInfoMapper, PaymentInfo> implements PaymentInfoService {
    /**
     * 记录支付日志：支付宝
     * @param params
     */
    @Override
    public void createPaymentInfoForAliPay(Map<String, String> params) {

        log.info("记录支付日志");

        //获取订单号
        String orderNo = params.get("out_trade_no");
        //业务编号
        String transactionId = params.get("trade_no");
        //交易状态
        String tradeStatus = params.get("trade_status");
        //交易金额
        String totalAmount = params.get("total_amount");
        int totalAmountInt = new BigDecimal(totalAmount).multiply(new BigDecimal("100")).intValue();


        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setOrderNo(orderNo);
        paymentInfo.setPaymentType(PayType.ALIPAY.getType());
        paymentInfo.setTransactionId(transactionId);
        paymentInfo.setTradeType("电脑网站支付");
        paymentInfo.setTradeState(tradeStatus);
        paymentInfo.setPayerTotal(totalAmountInt);

        Gson gson = new Gson();
        String json = gson.toJson(params, HashMap.class);
        paymentInfo.setContent(json);

        baseMapper.insert(paymentInfo);
    }
}
