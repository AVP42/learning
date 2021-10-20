package com.avp42.datastructure._9_countingSort_radixSort.prototype;

import java.util.Arrays;
import java.util.Random;

/**
 * @description: 基数排序  只能处理有符号整数排序
 *              在RadixSort中，如果是有符号的整数，会将负数排到整数的后面去
 * @author: wufc@viomi.com.cn
 * @create: 2021-06-08 20:29
 * @since： v 3.1.0
 */
public class RadixSort2 {
    // 对32位的int 整数数组，包括正负数，进行基数排序
    public void radixSort(int[] arr){
        int[] count = new int[65536];
        int[] temp = new int[arr.length];
        // low 16 bit sort
        // 利用与运算，求得第16位
        // 求尾坐标
        for (int num : arr) {
            count[num & 0xffff]++;
        }
        for(int i = 1; i < count.length; i++){
            count[i]  += count[i -1];
        }
        // 归位
        for(int i = arr.length - 1; i >= 0; i--){
            temp[--count[ low16(arr[i])]] = arr[i];
        }
        // high 16 bit sort
        Arrays.fill(count, 0);
        for(int num: arr){
            System.out.println("num: " + num + " high16: " + high16(num));
            count[high16(num)] ++;
        }
        for(int i = 1; i < count.length; i ++){
            count[i] += count[i - 1];
        }
        for(int i = arr.length -1; i >=0; i--){
            arr[--count[high16(temp[i])]] = temp[i];
        }
    }

    private int low16(int i) {
        return i & 0xffff;
    }

    // 处理负数情况
    private int high16(int i) {
        // 通过这种方式，如果是无符号的正整数，就会出现逆序
        // 但是对于有符号的证书，low16的值，正整数小到大顺序排列，负整数刚好从小到大排序，而且负整数与出来的值都要大于正整数与出来的值
        return 65535 - ((i & 0xffff0000) >>> 16);
    }

    public static void main(String[] args) {
        RadixSort2 obj = new RadixSort2();
        int[] arr = new int[10];
        Random random = new Random();
        for(int i = 0; i < 10; i ++){
//            arr[i] = random.nextBoolean() ? random.nextInt(3000) : -1 * random.nextInt(3000);
            arr[i] = random.nextInt(Integer.MAX_VALUE);
        }
        System.out.println(Arrays.toString(arr));
        obj.radixSort(arr);
        System.out.println(Arrays.toString(arr));

        // 负数的溢出
        byte a = 127;
        byte c = 1;
        byte b = -128;
        byte aa = (byte) (a + c);
        byte bb = (byte) (b - c);
        System.out.println(aa);
        System.out.println(bb);

        System.out.println(Math.pow(2, 15) - 1);
    }


}
