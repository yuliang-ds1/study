package com.alachao.study.chain;


public interface Filter {

    void doFilter(Request request,Response response,FilterChain chain);

}
