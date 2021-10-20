package com.avp42.basic.System;

import java.util.Scanner;

/**
 * @description:
 * @author: wufc@viomi.com.cn
 * @create: 2021-05-01 16:57
 * @since： v 3.1.0
 */
public class ScannerDemo {


    public static void main(String[] args) {
        // scanner 是java5的新特性。主要功能是简化文本扫描。类似一个带游标的扫描仪，支持根据正则分割式来扫描整个文本。
        // 使用分隔符（默认是空白）将输入分解为标记，使用不同的next来获取这些标记
        Scanner scanner = new Scanner(System.in);
        System.out.println("please enter your name!");
        // nextLine 分隔符是enter
        String line = scanner.nextLine();
        System.out.println("name" + line);

        System.out.println("please enter your age!");
        // next 分隔符是空格
        // 输入 12 g s

        int age = scanner.nextInt();
        String next = scanner.next();
        String next2 = scanner.next();
        System.out.println("age:"+age); // 输出 12
        System.out.println("age:"+next); // 输出s
        System.out.println("age:"+next2); // 输出g
    }

}
