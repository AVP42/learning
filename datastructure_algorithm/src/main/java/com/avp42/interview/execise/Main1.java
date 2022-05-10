package com.avp42.interview.execise;


import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


// 注意类名必须为 Main, 不要有任何 package xxx 信息

/**
 * 给一个由数字组成字符串的数组，求所有字符串拼接后数字最大，返回字符串。
 * 用例 [22,221] -> 返回 22221
 *
 * 通过率95%
 */
public class Main1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String line = in.nextLine();
            line = line.substring(1, line.length() - 1);
            String[] split = line.split(",");
            Arrays.sort(split, (x, y) -> (y + x).compareTo((x + y)));
            String ret = String.join("",split);
            int k = 0;
            while(k < ret.length() - 1 && ret.charAt(k) == '0'){
                k ++;
            }
            System.out.println(ret.substring(k));
        }
    }
}