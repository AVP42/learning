package com.avp42.datastructure._7_quick_sort.prototype;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @description: 快排v1版
 * @author: wufc@viomi.com.cn
 * @create: 2021-05-16 09:28
 * @since： v 3.1.0
 */
public class QuickSortV1 {

    public void sort(int[] arr, int l, int r) {
        if (arr == null || arr.length == 0) return;
        if (l > r) return;
        _sort(arr, l, r);
        System.out.println(Arrays.toString(arr));
    }

    public void _sort(int[] arr, int l, int r) {
        // 思想：定义一个基准，使用两个指针，一个从头遍历，一个从尾遍历，将区间分成比基准小的区间和比基准大的区间
        // 递归基
        if(l >= r) return;
        // 步骤: 1.x从最左边开始，将base存起来（避免多次交换），
        // 2.先从尾遍历，发现比base小的，移动到x，此时y坐在位置实际上是空，然后x自增，
        // 3.从头开始遍历，发现比base大的，移到到y，然后y，自增
        int x = l, y = r, base = arr[l];
        while(x<y){
            while(x<y && arr[y] >= base) y --;
            // 此时，如果x仍小于y，说明找到一个比base大的
            if(x < y) arr[x ++] = arr[y];
            // 和上面一个判断均可以带或者不带等于号，但是执行次数会不同，应该一个带，一个不带
            while(x<y && arr[x] < base) x ++;
            if(x < y) arr[y--] = arr[x];
        }
        // 最后x == y,且此处就是base应当处于的位置
        arr[x] = base;
        // 进行递归
        _sort(arr,l, x -1);
        _sort(arr,y + 1,r);
    }

    public static void main(String[] args) {
        int[] arr = {5, 2, 8, 6, 1, 3, 5, 4};
        new QuickSortV1().sort(arr, 0, 7);

    }
}