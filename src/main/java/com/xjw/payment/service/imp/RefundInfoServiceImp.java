package com.xjw.payment.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjw.payment.entity.OrderInfo;
import com.xjw.payment.entity.RefundInfo;
import com.xjw.payment.mapper.RefundInfoMapper;
import com.xjw.payment.service.OrderInfoService;
import com.xjw.payment.service.RefundInfoService;
import com.xjw.payment.util.OrderNoUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;


/**
 * @Author xjw
 * @Date 2022/8/11 16:14
 * @Version 1.0
 */
@Service
public class RefundInfoServiceImp extends ServiceImpl<RefundInfoMapper, RefundInfo> implements RefundInfoService {
    @Resource
    private OrderInfoService orderInfoService;




    /**
     * 根据订单号创建退款订单
     * @param orderNo
     * @return
     */
    @Override
    public RefundInfo createRefundByOrderNoForAliPay(String orderNo, String reason) {

        //根据订单号获取订单信息
        OrderInfo orderInfo = orderInfoService.getOrderByOrderNo(orderNo);

        //根据订单号生成退款订单
        RefundInfo refundInfo = new RefundInfo();
        refundInfo.setOrderNo(orderNo);//订单编号
        refundInfo.setRefundNo(OrderNoUtils.getRefundNo());//退款单编号

        refundInfo.setTotalFee(orderInfo.getTotalFee());//原订单金额(分)
        refundInfo.setRefund(orderInfo.getTotalFee());//退款金额(分)
        refundInfo.setReason(reason);//退款原因

        //保存退款订单
        baseMapper.insert(refundInfo);

        return refundInfo;
    }

    /**
     * 更新退款记录
     * @param refundNo
     * @param content
     * @param refundStatus
     */
    @Override
    public void updateRefundForAliPay(String refundNo, String content, String refundStatus) {

        //根据退款单编号修改退款单
        QueryWrapper<RefundInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("refund_no", refundNo);

        //设置要修改的字段
        RefundInfo refundInfo = new RefundInfo();
        refundInfo.setRefundStatus(refundStatus);//退款状态
        refundInfo.setContentReturn(content);//将全部响应结果存入数据库的content字段

        //更新退款单
        baseMapper.update(refundInfo, queryWrapper);

    }

}
