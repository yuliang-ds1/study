package com.alachao.study.thread.add;

import com.alachao.study.thread.test.ThreadA;

/**
 * @Author yuliang-ds1
 * @Date 2019/4/23 15:44
 * @Description
 */
public class TestAdd {



    public  static void main(String args[] ){

        /*AddService  addService=new AddService();

        for (int i=0;i<30000;i++) {
            ThreadAdd threadAdd = new ThreadAdd(addService);
            threadAdd.start();
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("result:"+addService.getNum());
*/






        String a="123";
        String b="123";
        String c=new String("123");
        String d=new String("123");

        System.out.println("result2:"+(a==b));

        System.out.println("result2:"+(a==c));

        System.out.println("result2:"+(b==c));

        System.out.println("result2:"+(c==d));


    }




}
