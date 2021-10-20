package com.avp42.leetcode.algorithms.mentu.easteregg.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * @description:
 * @author: wufc@viomi.com.cn
 * @create: 2021-04-18 14:21
 * @since： v 3.1.0
 */
public class StackEasterEgg {

    public static void main(String[] args) throws IOException {
        InputStream in = StackEasterEgg.class.getClassLoader().getResourceAsStream("stack.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line;
        int i = 0;
        long sum = 0;
        Stack<Integer> stack = new Stack<>();
        while((line = reader.readLine()) !=null){
            String[] s = line.split(" ");
            if(s[0].equals("push")){
                stack.push(Integer.valueOf(s[1]));
            }else{
                Integer pop = stack.pop();
                sum += ++i * pop;
            }
        }
        System.out.println(sum);
    }

}
