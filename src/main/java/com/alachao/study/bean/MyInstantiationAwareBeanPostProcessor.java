package com.alachao.study.bean;

/**
 * @Author yuliang-ds1
 * @Date 12:46  2018/4/20.
 * @Desciption
 */
import java.beans.PropertyDescriptor;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;

public class MyInstantiationAwareBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter {

    public MyInstantiationAwareBeanPostProcessor() {
        super();
        System.out.println("这是InstantiationAwareBeanPostProcessorAdapter实现类构造器！！");
    }

    // 接口方法、实例化Bean之前调用
    @Override
    public Object postProcessBeforeInstantiation(Class beanClass, String beanName) throws BeansException {
        System.out.println("InstantiationAwareBeanPostProcessor调用postProcessBeforeInstantiation方法beanName"+beanName);
        return null;
    }


    // 接口方法、实例化Bean之后调用
    @Override
    public boolean  postProcessAfterInstantiation(Object bean, String beanName)
            throws BeansException {
        System.out.println("InstantiationAwareBeanPostProcessor调用postProcessAfterInstantiation方法");
        System.out.println("InstantiationAwareBeanPostProcessor调用postProcessAfterInstantiation方法 beanName"+beanName);

        return false;
    }

    // 接口方法、实例化Bean之后调用
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {
        System.out.println("InstantiationAwareBeanPostProcessor调用postProcessAfterInitialization方法");
        System.out.println("InstantiationAwareBeanPostProcessor调用postProcessAfterInitialization方法 beanName"+beanName);

        return bean;
    }

    // 接口方法、设置某个属性时调用
    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs,
                                                    PropertyDescriptor[] pds,
                                                    Object bean, String beanName) throws BeansException {
        System.out.println("InstantiationAwareBeanPostProcessor调用postProcessPropertyValues方法");
        System.out.println("InstantiationAwareBeanPostProcessor调用postProcessPropertyValues方法 bean"+beanName);
        System.out.println("InstantiationAwareBeanPostProcessor调用postProcessPropertyValues方法 pvs"+pvs);


        return pvs;
    }
}
