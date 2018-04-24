package com.alachao.study.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author yuliang-ds1
 * @Date 12:44  2018/4/20.
 * @Desciption
 */
public class TestCreateBean {
    public static void main(String[] args) {

        System.out.println("现在开始初始化容器");

        ApplicationContext factory = new ClassPathXmlApplicationContext("beans.xml");
        System.out.println("容器初始化成功");
        //得到Preson，并使用
        System.out.println("开始获取bean");
        Person person = factory.getBean("person",Person.class);
        System.out.println(person);
        System.out.println("结束获取bean");

        System.out.println("现在开始关闭容器！");
        ((ClassPathXmlApplicationContext)factory).registerShutdownHook();
    }
}
