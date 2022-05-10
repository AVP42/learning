package com.avp42.interview.od;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

// 注意类名必须为 Main, 不要有任何 package xxx 信息

/**
 * 给字符串A和B。A和B长度相等且长度>=1。给整数V>=0。
 * 求A和B的最长连续公共子串，且子串在A中满足：|A[i]-B[i]|的和小于等于V，公式意思为A字串对应同位置的B字串字符之差的绝对值。
 *
 *
 * 用例：
 * xxcdefg
 * cdefghi
 * 5
 * 返回：2 (A中"cd" "de" "ef" "fg" 可以满足要求，|A[2]-B[2]|+|A[3]-B[3]|=4<V )
 *
 *
 * 通过率 25%
 */
public class Main3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String A = in.nextLine();
            String B = in.nextLine();
            if(A.length()!=B.length()){
                System.out.println(0);
            }
            int V = Integer.valueOf(in.nextLine());
            int length = maxLength(A, B, V);
            System.out.println(length);
        }
    }

    private static int maxLength(String a, String b, int v) {
        final HashMap<Character, List<Integer>> map = new HashMap<>();
        final int length = b.length();
        for (int i = 0; i < length; i++) {
            final char sub = b.charAt(i);
            if (!map.containsKey(sub)) {
                map.put(sub, new ArrayList<>());
            }
            map.get(sub).add(i);
        }
        int max = 0;
        for (int i = 0; i < length; i++) {
            final char sub = a.charAt(i);
            if (!map.containsKey(sub)) {
                continue;
            }
            final List<Integer> list = map.get(sub);
            for (final Integer j : list) {
                int k = 0;
                int sum = 0;
                while (i + k < length && j + k < length && a.charAt(i + k) == b.charAt(j + k)) {
                    sum += Math.abs(a.charAt(i + k) - b.charAt(i + k));
                    if (sum <= v) {
                        max = Math.max(max, k + 1);
                        k++;
                    } else {
                        break;
                    }
                }
            }
        }
        return max;
    }
}