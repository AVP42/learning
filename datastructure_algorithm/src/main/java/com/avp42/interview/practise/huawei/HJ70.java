package com.avp42.interview.practise.huawei;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
public class HJ70 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            int n = in.nextInt();
            int[][] arr = new int[n][2];
            for(int i = 0;i< n; i++){
                arr[i] = new int[]{in.nextInt(), in.nextInt()};
            }
            // 使用nextInt() 之后需要使用nextLine，需要先使用nextLine来读取上一个换行符只有才可以。
            in.nextLine();
            String line = in.nextLine();
            Stack<int[]> stack = new Stack<>();
            int ret = 0;
            for(int i  = 0; i < line.length(); i ++){
                char c = line.charAt(i);
                if(c == ')'){
                    ret += cal(stack);
                }else if(c == '('){
                    stack.push(null);
                }else{
                    stack.push(arr[c - 'A']);
                }
            }
            System.out.println(ret);
        }
    }

    private static int cal(Stack<int[]> stack) {
        int[] nums;
        int ret = 0;
        List<int[]> list = new ArrayList<>();
        while((nums = stack.pop())!= null){
            list.add(nums);
        }
        int n = list.size();
        int row = list.get(n- 1)[0] , column = list.get(n-1)[1];
        for(int i = n - 2 ;i >= 0; i --){
            ret += row * column * list.get(i)[1];
            column = list.get(i)[1];
        }
        stack.push(new int[]{row, column});
//        System.out.println(ret);
        return ret;
    }
}
