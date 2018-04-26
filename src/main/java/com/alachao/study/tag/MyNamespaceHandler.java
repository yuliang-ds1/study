package com.alachao.study.tag;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @Author yuliang-ds1
 * @Date 14:30  2018/4/26.
 * @Desciption
 */
public class MyNamespaceHandler extends NamespaceHandlerSupport{

    public void init() {
        registerBeanDefinitionParser("user",new UserBeanDefinitionParser());
    }
}
