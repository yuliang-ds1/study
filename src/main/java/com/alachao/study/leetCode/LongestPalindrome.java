package com.alachao.study.leetCode;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Author yuliang-ds1
 * @Date 2019/4/9 11:26
 * @Description
 *  给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
    示例 1：
    输入: "babad"
    输出: "bab"
    注意: "aba" 也是一个有效答案。
    示例 2：
    输入: "cbbd"
    输出: "bb"
 */
public class LongestPalindrome {

    public String longestPalindrome(String s) {
        int length = s.length();
        byte[] bytes = s.getBytes();
        List<String> list=new ArrayList<String>();
        long l = System.currentTimeMillis();

        for (int i =0;i<length-1;i++){
            byte index = bytes[i];
            for(int j=i;j<bytes.length-1;j++){
                if(index==bytes[j]){
                    String result;
                    if(i==j){
                        Byte b=bytes[j];
                        byte[] b1={b};
                        result=new String(b1);
                        String s1 = Byte.toString(b);
                    }else{
                        result=s.substring(i,j+1);
                    }

                    if(tryKnowHuiWen(result)){
                        list.add(result);
                    }
                }
            }
        }

        long l2= System.currentTimeMillis();
        System.out.println("time1：=="+(l2-l));
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(o1.length()>o2.length()){
                    return -1;
                }else if(o1.length()<o2.length()){
                    return 1;
                }else
                return 0;
            }
        });

        long l3 = System.currentTimeMillis();
        System.out.println("time1：=="+(l3-l2));
        if(list.size()>0)return   list.get(0);
        else
        return null;
    }


    public  boolean  tryKnowHuiWen(String s){
        StringBuilder reverse = new StringBuilder(s).reverse();
        if(reverse.toString().equals(s.toString())){
            return true;
        }else{
            return false;
        }
    }


    public static void  main(String args[]){

        LongestPalindrome longestPalindrome=new LongestPalindrome();
        long l = System.currentTimeMillis();
       // String input="abacdfgdcabababdewdebwbrebwrebwrbewbrbewbrbewbrbewbrbewbrbewbrbewbbrbwebrewbrkbwkejrewkbrkjewbrekjwbrkewbkjrbewjkrbkjewbrkjbkewjbrjwbrkjewbrjkbwerjkewkjrhkjewhrjkhewjkrhjkewhrkjewhkjrhejwkqhrkewhjrhejwhrjkewhrjehwjhjdhjewhjhdegdghewjhrqjhwjdhjwhedjkhkjewhjkdqhkjewhjqkdhejwkhdjkewhkqjdhkewhdkhewkjqhdkehwkjdhekwhqdhewkjdhkjewhjkdhewjqdhkjewhkdjhewjkqhdjkewhdjkhejwkhqdkjehwdkjhekwjhdjkehwjkdhwekhqdjkehwjkdhjkewhkjdhewkjqhdkehwkjqdhjwekhqdkjewhjqkdhewhkdhejkwhdkehwkjhdewhqdjewhqdkhewkhdkejkwhqdjehwkjdhkwehdkjewhdkjewhkjdhewkjhdewkhqudehwuhcdkuxskhdfgkdshfkjhdskjhfjkdshafjkhjwhjkdfewhdkjhwekhqdejkwhk";
        String input="abacdca";
        System.out.println("length：=="+input.length());
        String babad = longestPalindrome.longestPalindrome1(input);
        long l1 = System.currentTimeMillis();
        System.out.println("result：=="+(l1-l));
        long l2 = System.currentTimeMillis();



        System.out.println("result：=="+babad);
    }



    public String longestPalindrome1(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {

            int len1 = expandAroundCenter(s, i, i);
            System.out.println("for each len1 i:"+i+" len1:"+len1);
            int len2 = expandAroundCenter(s, i, i + 1);
            System.out.println("for each len1 i:"+i+" len1:"+len1);

            int len = Math.max(len1, len2);
            if (len> end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }

            System.out.println("len:"+len+"  start：=="+start+" end:"+end);
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }
}

