package com.tangyong.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Nacos配置
 */
@EnableAutoConfiguration
@Configuration
@Slf4j
public class NacosConfigConfiguration {

    @Bean(initMethod = "init")
    @ConditionalOnMissingBean
    @RefreshScope
    public DruidDataSource dataSource() {
        log.info("Init DruidDataSource");
        return new DruidDataSourceWrapper();
    }
}