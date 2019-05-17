package com.alachao.study.netty;

import org.msgpack.annotation.Message;

/**
 * @Author yuliang-ds1
 * @Date 2019/3/19 11:46
 * @Description
 */
@Message
public class UserInfo {

    public  UserInfo(){
    }


    private int  age;


    private String name;


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "UserInfo{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
