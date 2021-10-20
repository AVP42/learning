package com.avp42.basic.random;

import java.util.Arrays;
import java.util.Random;

/**
 * @description:
 * @author: fuchang.wu@foxmail.com
 * @date: 2021-07-31 12:42
 */
public class AllAboutRandom {
    public static void main(String[] args) {
        Random random = new Random(0);
        System.out.println(random.nextInt(1));
        System.out.println(1 | 0x8000000);
    }
}
