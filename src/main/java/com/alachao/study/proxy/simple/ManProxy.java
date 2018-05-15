package com.alachao.study.proxy.simple;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ManProxy   {


    //设计一个类变量记住代理类要代理的目标对象
    private Man man = new ManImpl();

    /**
     * 代理方法
     * @return
     */
    public Man getProxy() {
        return (Man) Proxy.newProxyInstance(ManProxy.class.getClassLoader(),
                man.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(
                            Object proxy,
                            Method method,
                            Object[] args) throws Throwable {
                            //如果调用的是代理对象的sing方法
                             if (method.getName().equals("sing")) {
                                System.out.println("我是他的经纪人，要找他唱歌得先给十万块钱！！");
                                //已经给钱了，经纪人自己不会唱歌，就只能找刘德华去唱歌！
                                return method.invoke(man, args);
                                //代理对象调用真实目标对象的sing方法去处理用户请求
                             }

                          return null;
                        }
                 });


    }
}


