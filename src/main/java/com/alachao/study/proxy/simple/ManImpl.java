package com.alachao.study.proxy.simple;

public class ManImpl  implements Man {

    @Override
    public String sing(String name) {
        System.out.println(name+"唱歌~~~");
        return null;
    }
}
