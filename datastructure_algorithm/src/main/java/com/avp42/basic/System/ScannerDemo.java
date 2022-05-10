package com.avp42.basic.System;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @description:
 *  scanner
 *  Scanner(System.in);
 *  scanner.next()   默认空格符   可以衍生出scanner.nextInt() scanner.nextByte()等等
 *  scanner。nextLine() 换行符
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


    static class Demo{
        public static void main(String[] args) {
            // 输入数组 [1,3,2,4,5,8]
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            String[] splited = line.substring(1, line.lastIndexOf("]")).split(",");
            int[] arr = new int[splited.length];
            for(int i = 0; i < splited.length; i++){
                arr[i] = Integer.parseInt(splited[i]);
            }
            System.out.println(Arrays.toString(arr));
        }
    }

    static class Demo1{
        public static void main(String[] args) {
            // 输入长度 6
            // 输入数组 1 3 2 4 5 8
            Scanner scanner = new Scanner(System.in);
            int n = scanner.nextInt();
            int[] arr = new int[n];
            for(int i =0;i< n; i ++){
                arr[i] = scanner.nextInt();
            }
            System.out.println(Arrays.toString(arr));
        }
    }

//    static class Demo2{
//        public static void main(String[] args) {
//            // 输入二维数组 [1,3], [2,4], [5,8]
//            Scanner scanner = new Scanner(System.in);
//            scanner.skip("\\[");
//            System.out.println(scanner.nextInt());
//            System.out.println(scanner.nextInt());
//            System.out.println(scanner.nextInt());
//        }
//    }

    static class Demo2_3{
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.println(scanner.nextInt());
            System.out.println(scanner.nextInt());
            System.out.println(scanner.nextLine());
        }
    }

    static class Demo3{
        public static void main(String[] args) {
            Scanner cin = new Scanner(System.in);
            // 如果没有while这个判断，那么处理完就会终止
            // 这个主要用于判断处理完后，还需要对新的数据进行处理
//            while(cin.hasNext()) {
                // 先读出有数据有多少长度
                int r = cin.nextInt();
                int c = cin.nextInt();
                int[][] matrix = new int[r][c];
                // 跳过行数和列数后的换行符, 如果是行的开始，就会将整个行返回
                // 如果后面想用nextLine()读取下一行，必须这里先用nextLine 读取换行符
                // nextInt() 不会消费到换行符
                System.out.println(cin.nextLine());
                for(int i=0;i<r;i++) {
                    for (int j = 0; j < c; j++) {
                        matrix[i][j] = cin.nextInt();
                    }
                }
                for(int i=0;i<r;i++) {
                        System.out.println(Arrays.toString(matrix[i]));
                }
//            }
        }
    }

}
