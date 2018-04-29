package com.alachao.study.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author yuliang-ds1
 * @Date 10:31  2018/4/28.
 * @Desciption
 */
@Component
public class Cao {

    public Cao(){
        System.out.println("【构造器】调用Cao的构造器实例化");
        System.out.println(toString());
        System.out.println();
    }

    @Value("${url}")
    private String url;

    private  String  a;

    private  String  b;


    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Cao{" +
                "url='" + url + '\'' +
                ", a='" + a + '\'' +
                ", b='" + b + '\'' +
                '}';
    }
}
