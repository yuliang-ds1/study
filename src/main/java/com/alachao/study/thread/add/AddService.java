package com.alachao.study.thread.add;

/**
 * @Author yuliang-ds1
 * @Date 2019/4/23 15:50
 * @Description
 */
public class AddService {


    private volatile int  num=0;

    public  void  add (){
       num=num+1;
    }


    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
