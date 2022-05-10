package com.avp42.basic.swap;

import java.util.LinkedList;
import java.util.List;

public class temp {

    public static void main(String[] args) {
        // 在java中-2是以补码的形式存在
        System.out.println( Integer.toBinaryString(-2));
        System.out.println( Integer.toBinaryString(-2 & 0x7fffffff));

        List<Integer> list = new LinkedList<>();
        list.add(2, 1);
        System.out.println(list);
    }
}
