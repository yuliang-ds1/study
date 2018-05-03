package com.alachao.study.aop;

import com.alachao.study.bean.Cao;
import com.alachao.study.bean.Person;
import com.alachao.study.bean.PersonWomen;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * @Author yuliang-ds1
 * @Date 9:47  2018/4/26.
 * @Desciption
 */
public class TestBean   implements BeanFactoryAware, BeanNameAware,
        InitializingBean, DisposableBean {

    @Value("${initialSize}")
    private String initialSize;

    @Value("${maxActive}")
    private String maxActive;

    @Autowired
    private Cao cao;

    @Resource
    private Person person;

    private PersonWomen  personWomen;

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


    public String getInitialSize() {
        return initialSize;
    }

    public void setInitialSize(String initialSize) {
        this.initialSize = initialSize;
    }

    public String getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(String maxActive) {
        this.maxActive = maxActive;
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
        System.out.println("TestBean【destroy-method】调用<bean>的destroy-method方法");

    }
    @PreDestroy
    public void PreDestroy() {
        System.out.println("TestBean【PreDestroy】调用<bean>的PreDestroy方法");

    }

    public Cao getCao() {
        return cao;
    }

    public void setCao(Cao cao) {
        this.cao = cao;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public PersonWomen getPersonWomen() {
        return personWomen;
    }

    public void setPersonWomen(PersonWomen personWomen) {
        this.personWomen = personWomen;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public String getBeanName() {
        return beanName;
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "initialSize='" + initialSize + '\'' +
                ", maxActive='" + maxActive + '\'' +
                ", cao=" + cao +
                ", person=" + person +
                ", personWomen=" + personWomen +
                ", testStr='" + testStr + '\'' +
                ", beanFactory=" + beanFactory +
                ", beanName='" + beanName + '\'' +
                '}';
    }
}
