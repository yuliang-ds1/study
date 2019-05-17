package com.alachao.study.leetCode;

/**
 * @Author yuliang-ds1
 * @Date 2019/4/9 14:50
 * @Description
 */
public class isPalindrome {


    public boolean testalindrome(int x) {

        String s=String.valueOf(x);
        StringBuilder sb=new StringBuilder(s);
        sb.reverse();
        if(sb.toString().equals(s)){
            return true;
        }else{
            return false;
        }

    }


    public static void main(String args[]){

        isPalindrome  isPalindrome=new isPalindrome();
        boolean testalindrome = isPalindrome.testalindrome(121);

        System.out.println("result:"+testalindrome);

    }

}
