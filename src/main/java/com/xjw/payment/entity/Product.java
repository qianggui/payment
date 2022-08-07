package com.xjw.payment.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author xjw
 * @Date 2022/8/7 10:15
 * @Version 1.0
 */
@Data
@TableName("t_product")
public class Product extends BaseEntity {
    private String title; //商品名称

    private Integer price; //价格（分）
}
