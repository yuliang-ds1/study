package com.alachao.study.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author yuliang-ds1
 * @Date 16:41  2018/4/27.
 * @Desciption
 */
@Configuration
@PropertySource({"classpath:db_server.properties"})
public class PropertiesConfig {

}
