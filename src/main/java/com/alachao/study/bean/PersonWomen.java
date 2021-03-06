package com.alachao.study.bean;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Author yuliang-ds1
 * @Date 10:05  2018/4/20.
 * @Desciption
 */
@Component
public class PersonWomen implements BeanFactoryAware, BeanNameAware,
        InitializingBean, DisposableBean {

    private String name="PersonWomenyYuliang";
    private String address="PersonWomenLiang";
    private int phone;

    private BeanFactory beanFactory;
    private String beanName;

    public PersonWomen() {
        System.out.println("【构造器】调用PersonWomen的构造器实例化");
        System.out.println(toString());
        System.out.println();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("PersonWomen【注入属性】注入属性name ："+this.name);
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        System.out.println("PersonWomen【注入属性】注入属性address： "+this.address);
        this.address = address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        System.out.println("PersonWomen【注入属性】注入属性phone： "+this.address);
        this.phone = phone;
    }

    // 这是BeanFactoryAware接口方法
    @Override
    public void setBeanFactory(BeanFactory arg0) throws BeansException {
        System.out.println("PersonWomen【BeanFactoryAware接口】调用BeanFactoryAware.setBeanFactory()");
        System.out.println(toString());
        System.out.println();
        this.beanFactory = arg0;
    }

    // 这是BeanNameAware接口方法
    @Override
    public void setBeanName(String arg0) {
        System.out.println("PersonWomen【BeanNameAware接口】调用BeanNameAware.setBeanName()");
        this.beanName = arg0;
        System.out.println(toString());
        System.out.println();

    }

    // 这是InitializingBean接口方法
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("PersonWomen【InitializingBean接口】调用InitializingBean.afterPropertiesSet()");
        System.out.println(toString());
        System.out.println();

    }

    // 这是DiposibleBean接口方法
    @Override
    public void destroy() throws Exception {
        System.out.println("PersonWomen【DiposibleBean接口】调用DiposibleBean.destory()");
    }

    // 通过<bean>的init-method属性指定的初始化方法
    @PostConstruct
    public void myInit() {
        System.out.println("PersonWomen【init-method】调用<bean>的init-method属性指定的初始化方法");
        System.out.println(toString());
        System.out.println();

    }

    // 通过<bean>的destroy-method属性指定的初始化方法
    public void myDestory() {
        System.out.println("PersonWomen【destroy-method】调用<bean>的destroy-method属性指定的初始化方法");
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone=" + phone +
                ", beanFactory=" + beanFactory +
                ", beanName='" + beanName + '\'' +
                '}';
    }
}