package com.alachao.study;

public class Test {



    public  static void  main(String args[]){
        int i=0;

        DiamondManager manager = new DefaultDiamondManager("xiaoyu", new ManagerListener() {
            @Override
            public Executor getExecutor() {
                return null;
            }

            @Override
            public void receiveConfigInfo(String configInfo) {
                System.out.println("receive config: " + configInfo);
            }
        });


        while(i>10000){
            System.out.println("=======while=======");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
