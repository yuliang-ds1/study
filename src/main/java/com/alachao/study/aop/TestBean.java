package com.alachao.study.aop;

/**
 * @Author yuliang-ds1
 * @Date 9:47  2018/4/26.
 * @Desciption
 */
public class TestBean {

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
}
