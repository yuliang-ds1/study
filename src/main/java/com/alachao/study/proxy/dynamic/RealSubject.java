package com.alachao.study.proxy.dynamic;

public class RealSubject implements  Subject {
    private  String  content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void doSomething()
    {
        System.out.println( "call doSomething()" );
    }

}
