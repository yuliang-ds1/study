package com.alachao.study.leetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author yuliang-ds1
 * @Date 2019/3/4 10:50
 * @Description
 */
public class Solution {

   /* 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
    你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。

    示例:
    给定 nums = [2, 7, 11, 15], target = 9
    因为 nums[0] + nums[1] = 2 + 7 = 9
    所以返回 [0, 1]*/

    public int[] twoSum(int[] nums, int target) {

        Map<Integer,Integer> hash=new HashMap<Integer,Integer>();

        for(int i=0;i<nums.length;i++){
           int current=nums[i];
           int result=target-current;
           if(hash.containsKey(result)){
               System.out.println(" one "+hash.get(result)+",two "+i);
               return new int[]{hash.get(result),i};
           }else{
               hash.put(current,i);
           }
        }
        throw  new RuntimeException("没有结果集");
    }

    public  static void main(String args[]){

        int[]  num ={2, 7, 11, 15};
        Solution solution=new Solution();
        solution.twoSum(num,9);


    }
}
