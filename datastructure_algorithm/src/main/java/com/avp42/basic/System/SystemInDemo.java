package com.avp42.basic.System;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @description:
 * @author: wufc@viomi.com.cn
 * @create: 2021-05-01 17:02
 * @since： v 3.1.0
 */
public class SystemInDemo {
    public static void main(String[] args) throws IOException {
        System.out.println("please enter your name:");
        // 可以获取从键盘中输入的字符，但是只能获取一个字符，比如这里输入alien
        char name = (char) System.in.read(); // 读取到a
        char name2 = (char) System.in.read(); // 读取到l
        System.out.println("your name is:" + name); // 标准输出流输出a
        System.err.println("your name is:" + name2); // 标准错误流输出l

    }

}
