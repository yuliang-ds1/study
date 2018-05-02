package com.alachao.study.spring_mybatis;

import com.alachao.study.mybatis.UserMapper;
import com.alachao.study.mybatis.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author yuliang-ds1
 * @Date 9:47  2018/5/2.
 * @Desciption
 */
public class TestSpringMybatis {

    /**
     * 测试类
     *
     * @param args
     */
    public static void main(String args[]){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("mybatis/applicationContext.xml");
/*
        UserMapper userMapper=(UserMapper) applicationContext.getBean("userMapper");
*/

        UserService userService=(UserService) applicationContext.getBean("userService");
        System.out.print(userService.getUser(1).toString());
    }
}
