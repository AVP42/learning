package com.avp42.interview.od;


import java.util.Arrays;
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
            String a = in.nextLine();
            if(a==null||a.length()==0){
                System.out.println("");
            }
            final String[] split = a.split(",");
            if(split.length==1){
                System.out.println(split[0]);
            }
            final List<String> list = Arrays.stream(split).sorted((o1, o2) -> selfCompare(o2, o1))
                    .collect(Collectors.toList());
            final String res = list.stream().collect(Collectors.joining());
            System.out.println(res);
        }
    }

    private static int selfCompare(String o1, String o2) {
        if (o1.equals(o2)) {
            return 0;
        }
        int i = 0;
        while (i < o1.length() && i < o2.length()) {
            final int sub = o1.charAt(i) - o2.charAt(i);
            if (sub == 0) {
                i++;
                continue;
            } else {
                return sub;
            }
        }
        while (i < o1.length()) {
            final int sub = o1.charAt(i) - o2.charAt(i - o2.length());
            if (sub == 0) {
                i++;
                continue;
            } else {
                return sub;
            }
        }
        while (i < o2.length()) {
            final int sub = o2.charAt(i) - o1.charAt(i - o1.length());
            if (sub == 0) {
                i++;
                continue;
            } else {
                return -sub;
            }
        }
        return 0;
    }


}