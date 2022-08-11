package com.xjw.payment.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjw.payment.entity.OrderInfo;
import com.xjw.payment.entity.Product;
import com.xjw.payment.enums.OrderStatus;
import com.xjw.payment.mapper.OrderInfoMapper;
import com.xjw.payment.mapper.ProductMapper;
import com.xjw.payment.service.OrderInfoService;
import com.xjw.payment.util.OrderNoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

/**
 * @Author xjw
 * @Date 2022/8/8 15:07
 * @Version 1.0
 */
@Slf4j
@Service
public class OrderInfoServiceImp  extends ServiceImpl<OrderInfoMapper, OrderInfo> implements OrderInfoService {

    @Resource
    private ProductMapper productMapper;

    /**
     * 查询订单列表，并倒序查询
     * @return
     */
    @Override
    public List<OrderInfo> listOrderByCreateTimeDesc(int userId) {

        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<OrderInfo>().eq("user_id",userId) .orderByDesc("create_time");
        return baseMapper.selectList(queryWrapper);
    }

    /**
     * 根据订单号获取订单状态
     * @param orderNo
     * @return
     */
    @Override
    public String getOrderStatus(String orderNo) {

        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_no", orderNo);
        OrderInfo orderInfo = baseMapper.selectOne(queryWrapper);
        if(orderInfo == null){
            return null;
        }
        return orderInfo.getOrderStatus();
    }


    @Override
    public OrderInfo createOrderByProductId(Long productId, String paymentType) {

        //查找已存在但未支付的订单
        OrderInfo orderInfo = this.getNoPayOrderByProductId(productId, paymentType);
        if( orderInfo != null){
            return orderInfo;
        }

        //获取商品信息
        Product product = productMapper.selectById(productId);

        //生成订单
        orderInfo = new OrderInfo();
        orderInfo.setTitle(product.getTitle());
        orderInfo.setOrderNo(OrderNoUtils.getOrderNo()); //订单号
        orderInfo.setProductId(productId);
        orderInfo.setTotalFee(product.getPrice()); //单位是:分
        orderInfo.setOrderStatus(OrderStatus.NOTPAY.getType()); //未支付
        orderInfo.setPaymentType(paymentType);
        baseMapper.insert(orderInfo);

        return orderInfo;
    }

    /**
     * 根据商品id查询未支付订单
     * 防止重复创建订单对象
     * @param productId
     * @return
     */
    private OrderInfo getNoPayOrderByProductId(Long productId, String paymentType) {

        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id", productId);
        queryWrapper.eq("order_status", OrderStatus.NOTPAY.getType());
        queryWrapper.eq("payment_type", paymentType);
//        queryWrapper.eq("user_id", userId);
        OrderInfo orderInfo = baseMapper.selectOne(queryWrapper);
        return orderInfo;
    }


    /**
     * 根据订单号更新订单状态
     * @param orderNo
     * @param orderStatus
     */
    @Override
    public void updateStatusByOrderNo(String orderNo, OrderStatus orderStatus) {

        log.info("更新订单状态 ===> {}", orderStatus.getType());

        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_no", orderNo);

        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderStatus(orderStatus.getType());

        baseMapper.update(orderInfo, queryWrapper);
    }

    
    /**
     * 根据订单号获取订单
     * @param orderNo
     * @return
     */
    @Override
    public OrderInfo getOrderByOrderNo(String orderNo) {

        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_no", orderNo);
        OrderInfo orderInfo = baseMapper.selectOne(queryWrapper);

        return orderInfo;
    }


    /**
     * 查询创建超过minutes分钟并且未支付的订单
     * @param minutes
     * @return
     */
    @Override
    public List<OrderInfo> getNoPayOrderByDuration(int minutes, String paymentType) {

        Instant instant = Instant.now().minus(Duration.ofMinutes(minutes));

        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_status", OrderStatus.NOTPAY.getType());
        queryWrapper.le("create_time", instant);
        queryWrapper.eq("payment_type", paymentType);

        List<OrderInfo> orderInfoList = baseMapper.selectList(queryWrapper);

        return orderInfoList;
    }
}
