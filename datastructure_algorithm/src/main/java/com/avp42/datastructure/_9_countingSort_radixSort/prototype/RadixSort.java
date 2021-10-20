package com.avp42.datastructure._9_countingSort_radixSort.prototype;

import java.util.Arrays;
import java.util.Random;

/**
 * @description: 基数排序
 * @author: wufc@viomi.com.cn
 * @create: 2021-06-08 20:29
 * @since： v 3.1.0
 */
public class RadixSort {
    // 对32位的int 正整数数组，进行基数排序
    public void radixSort(int[] arr){
        int[] count = new int[65536];
        int[] temp = new int[arr.length];
        // low 16 bit sort
        // 利用与运算，求得第16位
        // 求尾坐标
        for (int num : arr) {
            System.out.println("num: " + num + " low16: " + low16(num));
            count[low16(num)]++;
        }
        for(int i = 1; i < count.length; i++){
            count[i]  += count[i -1];
        }
        // 归位
        for(int i = arr.length - 1; i >= 0; i--){
            temp[--count[ low16(arr[i])]] = arr[i];
        }
        System.out.println(Arrays.toString(temp));
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
        // num: -172 low16: 65364
        // 负数的位与运算  负数与正数 使用负数的补码与正数的原码来与操作 所以负数越小，与的结果越大，所以在有负数的情况下，负数部分依然可以正确排序
        return i & 0xffff;
    }

    private int high16(int i) {
        return (i & 0xffff0000) >>> 16;
    }

    public static void main(String[] args) {
        RadixSort obj = new RadixSort();
        int[] arr = new int[10];
        Random random = new Random();
        for(int i = 0; i < 10; i ++){
            arr[i] = random.nextBoolean() ? random.nextInt(200) : -1 * random.nextInt(200);
        }
        System.out.println(Arrays.toString(arr));
        obj.radixSort(arr);
        System.out.println(Arrays.toString(arr));

        System.out.println(-5 >> 1);
        System.out.println(-5 >>> 1);
        System.out.println(-2 & 2);
    }


}
