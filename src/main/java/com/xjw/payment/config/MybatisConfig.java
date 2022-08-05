package com.xjw.payment.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author xjw
 * @Date 2022/8/5 11:17
 * @Version 1.0
 */
@Configuration
@MapperScan("com.xjw.payment.mapper")
@EnableTransactionManagement
public class MybatisConfig {
}
