package com.alachao.study.io.socket;

public class TestSocket {

    private  String myname;

    @Override
    public String toString() {
        return super.toString();
    }

    public String getMyname() {
        return myname;
    }

    public void setMyname(String myname) {
        this.myname = myname;
    }

    public static  void main(String args[]) throws RuntimeException {

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        throw  new RuntimeException("");

    }
}
