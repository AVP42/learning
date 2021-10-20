package com.avp42.basic;

import java.util.*;

/**
 * @description:
 * @author: wufc@viomi.com.cn
 * @create: 2021-06-17 20:32
 * @since： v 3.1.0
 */
public class Main {
    public static void main(String[] args) {
//        System.out.println(1 << 0);
//
//        Random random = new Random(System.currentTimeMillis());
//        "ss".chars().map(x -> x < 97 ? x + 32 : x - 32);
//
//        System.out.println(-1/2);
//
//        int[][] ints = new int[9][8];

        System.out.println(2 & -2);
        System.out.println(3 & -3);

        System.out.println(Collections.nCopies(10, 1));
        System.out.println(new StringBuffer().length());

        System.out.println("floor".indexOf("gr"));


        System.out.println(-10 / 5);
        System.out.println(-2 % 5);
        System.out.println(-5 % 5);


        System.out.println(Math.log(100) - 1e-9);
        System.out.println(Math.log(100));
        System.out.println(Math.log(10) - Math.log(5));


    }
}


