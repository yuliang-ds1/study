package com.alachao.study.aop;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

import javax.annotation.PostConstruct;

/**
 * @Author yuliang-ds1
 * @Date 9:47  2018/4/26.
 * @Desciption
 */
public class TestBean   implements BeanFactoryAware, BeanNameAware,
        InitializingBean, DisposableBean {

    public TestBean(){
        System.out.println("TestBean 【构造器】调用TestBean的构造器实例化");
        System.out.println(toString());
    }
    private  String  testStr="testStr";

    public String getTestStr() {
        return testStr;
    }

    public void setTestStr(String testStr) {
        this.testStr = testStr;
    }

    public  void test(){
        System.out.println("test代理");
    }

   public String toString(){
        return "testStr="+testStr;
   }

    private BeanFactory beanFactory;
    private String beanName;

    // 这是BeanFactoryAware接口方法
    @Override
    public void setBeanFactory(BeanFactory arg0) throws BeansException {
        System.out.println("TestBean【BeanFactoryAware接口】调用BeanFactoryAware.setBeanFactory()");
        System.out.println(toString());
        this.beanFactory = arg0;
    }

    // 这是BeanNameAware接口方法
    @Override
    public void setBeanName(String arg0) {
        System.out.println("TestBean【BeanNameAware接口】调用BeanNameAware.setBeanName()");
        this.beanName = arg0;
        System.out.println(toString());
        System.out.println();

    }

    // 这是InitializingBean接口方法
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("TestBean【InitializingBean接口】调用InitializingBean.afterPropertiesSet()");
        System.out.println(toString());
        System.out.println();

    }

    // 这是DiposibleBean接口方法
    @Override
    public void destroy() throws Exception {
        System.out.println("TestBean【DiposibleBean接口】调用DiposibleBean.destory()");
    }

    // 通过<bean>的init-method属性指定的初始化方法
    @PostConstruct
    public void init() {
        System.out.println("TestBean【init-method】调用<bean>的init-method属性指定的初始化方法");
        System.out.println(toString());
        System.out.println();

    }

    // 通过<bean>的destroy-method属性指定的初始化方法
    public void myDestory() {
        System.out.println("TestBean【destroy-method】调用<bean>的destroy-method属性指定的初始化方法");

    }

}