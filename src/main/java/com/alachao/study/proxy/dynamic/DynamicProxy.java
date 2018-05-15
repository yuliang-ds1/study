package com.alachao.study.proxy.dynamic;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy {

    public static void main( String args[] )
    {
        RealSubject real = new RealSubject();
        Subject proxySubject = (Subject) Proxy.newProxyInstance(Subject.class.getClassLoader(),
                new Class[]{Subject.class},
                new ProxyHandler(real));

        proxySubject.doSomething();
        System.out.println(Subject.NAME_PACE);

        try {
            Method method= Class.forName("com.alachao.study.proxy.dynamic.Subject").getMethod("doSomething", new Class[0]);


            System.out.println(method.getName());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //write proxySubject class binary data to file
        createProxyClassFile();
    }

    public static void createProxyClassFile()
    {
        String name = "ProxySubject";
        byte[] data = ProxyGenerator.generateProxyClass( name, new Class[] { Subject.class } );
        try
        {
            FileOutputStream out = new FileOutputStream( name + ".class" );
            out.write( data );
            out.close();
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }


}
