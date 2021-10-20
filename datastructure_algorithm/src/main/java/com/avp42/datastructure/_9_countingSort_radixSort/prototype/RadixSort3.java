package com.avp42.datastructure._9_countingSort_radixSort.prototype;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

/**
 * @description: 基数排序  对有无符号整数都起作用
 *              在RadixSort中，如果是有符号的整数，会将负数排到整数的后面去
 * @author: wufc@viomi.com.cn
 * @create: 2021-06-08 20:29
 * @since： v 3.1.0
 */
public class RadixSort3 {
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
            System.out.println("num: " + num + " __high16: " + __high16(num) + " high16: " + high16(num));
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

        return __high16(i) > 32767 ? __high16(i) - 32768 : __high16(i) + 32768;
    }

    private int __high16(int i) {

        return (i & 0xffff0000) >>> 16;
    }

    public static void main(String[] args) {
        RadixSort3 obj = new RadixSort3();
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

        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(4);
        list.add(5);
        Integer[] integers = list.stream().toArray(value -> new Integer[0]);
        int[] array = list.stream().mapToInt(x -> x).toArray();
        System.out.println(Arrays.toString(integers));
        System.out.println(Arrays.toString(array));

        Arrays.stream(new int[]{1, 3, 2}).boxed().collect(Collectors.toList());
    }


}
