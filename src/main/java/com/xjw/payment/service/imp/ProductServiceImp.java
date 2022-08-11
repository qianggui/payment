package com.xjw.payment.service.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjw.payment.entity.Product;
import com.xjw.payment.mapper.ProductMapper;
import com.xjw.payment.service.ProductService;
import org.springframework.stereotype.Service;

/**
 * @Author xjw
 * @Date 2022/8/11 16:37
 * @Version 1.0
 */

@Service
public class ProductServiceImp extends ServiceImpl<ProductMapper, Product> implements ProductService {

}
