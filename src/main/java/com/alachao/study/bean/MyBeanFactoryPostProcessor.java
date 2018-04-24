package com.alachao.study.bean;

/**
 * @Author yuliang-ds1
 * @Date 12:47  2018/4/20.
 * @Desciption
 */
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import java.util.List;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    public MyBeanFactoryPostProcessor() {
        super();
        System.out.println("这是BeanFactoryPostProcessor实现类构造器！！");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory arg0)throws BeansException {
        System.out.println("BeanFactoryPostProcessor调用postProcessBeanFactory方法");
        BeanDefinition bd = arg0.getBeanDefinition("person");
        MutablePropertyValues list=bd.getPropertyValues();
        List<PropertyValue> l=list.getPropertyValueList();
        for (org.springframework.beans.PropertyValue p:l) {
            System.out.println("MyBeanFactoryPostProcessor 打印："+p.toString());
        }
        bd.getPropertyValues().addPropertyValue("phone", "110");
    }

}