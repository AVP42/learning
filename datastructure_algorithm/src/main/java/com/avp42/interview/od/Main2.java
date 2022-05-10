package com.avp42.interview.od;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.stream.Collectors;


// 注意类名必须为 Main, 不要有任何 package xxx 信息

/**
 * 给一个数字，求该数字是否可以分解为2个质数相乘。可以的话按升序返回2个质数，空格分开。如果不行返回"-1 -1"
 * 用例 15->[3 5]
 * 用例 27->[-1 -1]
 *
 * 通过率35%
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int a = in.nextInt();
            final ArrayList<Integer> list = getNum(a);
            final String result = list.stream().sorted().map(item -> String.valueOf(item)).collect(Collectors.joining(" "));
            System.out.println(result);
        }
    }

    private static ArrayList<Integer> getNum(int a) {
        if (a <= 3) {
            System.out.println("-1 -1");
        }
        final ArrayList<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(3);
        outer:
        for (int i = 4; i < a; i++) {
            for (final Integer sub : list) {
                if (i % sub == 0) {
                    continue outer;
                }
            }
            list.add(i);
        }
        final HashSet<Integer> set = new HashSet<>(list);
        final ArrayList<Integer> res = new ArrayList<>();
        for (final Integer sub : set) {
            if (a % sub == 0) {
                final int tmp = a / sub;
                if (tmp != sub && set.contains(tmp)) {
                    res.add(sub);
                    res.add(tmp);
                } else {
                    res.add(-1);
                    res.add(-1);
                }
                break;
            }
        }
        if (res.size() == 0) {
            res.add(-1);
            res.add(-1);
        }
        return res;
    }


}