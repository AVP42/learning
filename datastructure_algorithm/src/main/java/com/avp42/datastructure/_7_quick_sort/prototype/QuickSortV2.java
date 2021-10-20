package com.avp42.datastructure._7_quick_sort.prototype;

import java.util.Arrays;

/**
 * @description: v2版，单边递归法，由于递归是在最后面的(尾递归)，类似前序遍历序列，是可以比较方便的将其改为只有一个递归，从而减少递归次数
 * @author: wufc@viomi.com.cn
 * @create: 2021-05-16 10:07
 * @since： v 3.1.0
 */
public class QuickSortV2 {
    public void sort(int[] arr,int l,int r){
        if(arr ==  null || arr.length ==0) return ;
        if(l >=r) return;
        _sort(arr,l,r);
    }

    public void _sort(int[] arr, int l, int r){
        while(l < r){
            int x = l, y = r, base= arr[l];
            while(x< y){
                while(x < y && arr[y] >= base) y --;
                if(x < y) arr[x++] = arr[y];
                while(x < y && arr[x] < base) x ++;
                if(x < y) arr[y--] = arr[x];
            }
            arr[x] = base;
            // 通过转变边界，使得循环可以继续运行一次，这样去掉了一次递归
            _sort(arr,x + 1, r);
            // 将r 转变为 左边递归的边界，使得while循环里面的相当于执行了一次递归
            // 当然，转换有边界也是可以的
            // 还可以通过栈结构来彻底转变为迭代解法
            r = x -1;
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 1, 8, 6, 6, 9, 2, 4, 3};
        new QuickSortV2().sort(arr, 0, 8);
        System.out.println(Arrays.toString(arr));
    }

}
