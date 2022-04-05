package com.avp42.datastructure.chapter6.exercise;

import java.util.Stack;

/**
 * @author wufc@viomi.com.cn
 * @since 2022/4/4 0004
 */
public class MonotonyStack {

    public void nearestGreater(int[] arr){
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < arr.length; i ++){
            while(!stack.isEmpty() && arr[stack.peek()] > arr[i]) stack.pop();
            if(!stack.isEmpty()){
                System.out.println("最近大于" + arr[i] + "的是：" + arr[stack.peek()]);
            }else{
                System.out.println("没有最近大于" + arr[i] + "的元素");
            }
            stack.push(i);
        }
    }
}
