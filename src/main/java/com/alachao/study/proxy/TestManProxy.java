package com.alachao.study.proxy;

public class TestManProxy {


    public  static void main(String args[]){

        ManProxy  manProxy=new ManProxy();
        Man  man=manProxy.getProxy();
        man.sing("刘德华");


    }

}
