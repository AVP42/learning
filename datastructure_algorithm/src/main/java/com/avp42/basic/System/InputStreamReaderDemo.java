package com.avp42.basic.System;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @description:
 * @author: wufc@viomi.com.cn
 * @create: 2021-05-01 22:24
 * @since： v 3.1.0
 */
public class InputStreamReaderDemo {
    public static void main(String[] args) {

        // 使用流 比如使用InputStreamReader 或者 再来一层 BufferReader
        System.out.println("please enter your name again:");
        // InputStreamReader 是字符流和字节流之间的桥梁
        java.io.InputStreamReader inputStreamReader = new java.io.InputStreamReader(System.in);
        // BufferedReader 是更高级的reader，封装了一行一行字符的缓冲方式文本读取。
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line = null;
        try {
            line = bufferedReader.readLine();
        } catch (IOException e) {


        }
        System.out.println("your name is:" + line);
    }

}
