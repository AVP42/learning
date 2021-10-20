package com.avp42.datastructure._7_quick_sort.prototype;

import java.util.Arrays;

/**
 * @description: 内省排序，在v2的基础上
 *              1. 优化迭代过程，更少的比较次数
 *              2. 无监督插入排序，使用插入排序充分利用cpu高速缓存，并且使用无监督减少判断次数
 *              3. 三点取中选基准，基准选择过差，导致递归深度多大
 * @author: wufc@viomi.com.cn
 * @create: 2021-05-16 10:45
 * @since： v 3.1.0
 */
public class IntroSort {



    public static void main(String[] args) {
        int[] arr = {5, 4, 2, 8, 1, 3, 6, 7};
        int[] arr2 = {5, 4, 2, 8, 1, 3, 6, 7};
        int[] arr3 = {5, 4, 2, 8, 1, 3, 6, 7};
        int[] arr4 = {5, 4, 2, 8, 1, 3, 6, 7};
        int[] arr5 = {5, 4, 2, 8, 1, 3, 6, 7};

//        // 插入排序1
//        new IntroSort().insertionSort(arr, 0, 7);
//        System.out.println(Arrays.toString(arr));
//        // 插入排序2
//        new IntroSort().insertionSortV2(arr2, 0, 7);
//        System.out.println(Arrays.toString(arr2));
//        // 插入排序3-无监督
//        new IntroSort()._unguarded_insertionSort(arr3, 0, 7);
//        System.out.println(Arrays.toString(arr3));
//        // 快速排序-三点取中
//        new IntroSort()._quickSortV3(arr4, 0, 7);
//        System.out.println(Arrays.toString(arr4));
        // 内省排序
        new IntroSort()._sort(arr5, 0, 7);
        System.out.println(Arrays.toString(arr5));
    }

    public static final int threshold = 4;

    public void sort(int[] arr, int l ,int r){
        if(arr == null || arr.length == 0) return;
        if(l >=r ) return;
        _sort(arr,l,r);
    }

    public void _sort(int[] arr, int l, int r){
        // 先进行大区间的快排
        _quickSort(arr, l, r);
        // 然后进行整体的插入排序，与逆序对相关，利用从当前位置往前遍历sorted序列，然后冒泡，从而使得不必每次都从整个序列的最初开始
        // 并不是在一个个小区间里面进行插入排序，而是对整体进行插入排序，由于熵已经降低，逆序对个数已经下降，插入排序将会比较快
        _unguarded_insertionSort(arr,l,r);
    }

    private void _quickSort(int[] arr, int l, int r) {
        while(r - l > threshold) {
            int x = l, y = r, base = mid(arr[l], arr[(l + r) / 2], arr[r]);
            do {
                // 下面两个循环使用等于号均不行
                // 考察 2 0 2 1 1 0
                // 一方面会出界
                // 另一方面，即时使用了限制x,y的边界，使用等于号，
                // 在基准取到当前区间的最大值2时，arr[x] <= base 就会死循环
                // 在基准取到当前区间的最大值0时，arr[y] >= base 也会死循环
                while (arr[x] < base) x++;
                while (arr[y] > base) y--;
                // 有可能出现x == y ，值都等于base，但是x和y也需要再往前走
                if (x <= y) {
                    swap(arr, x, y);
                    x++;
                    y--;
                }
            } while (x <= y);
            // 最终总是 ... y,x,....的顺序
            _quickSort(arr,x,r);
            r = y;
        }
    }


    private void _quickSortV3(int[] arr, int l, int r) {
        while(l < r) {
            int x = l, y = r, base = mid(arr, l, r);
            do {
                // 这里已经不是交叉迭代(最终会相聚于一点)了，这里x有可能大于y
                while(arr[y] > base) {
                    y --;
                }
                while(arr[x] < base) x ++;
                System.out.println("inline---- base:" + base + " x:"+x+" y:"+y);
                // 这些等号与wile(x<=y)的等号是不能省略的
                if(x <= y){
                    swap(arr, x, y);
                    x++; y --;
                }
            } while (x <= y);
            System.out.println("outline---- x:"+x+" y:"+y);
            _quickSortV3(arr, x , r);
            r = y;
        }
    }


    // 普通的插入排序--- 在sortedSet中顺序查找
    private void insertionSort(int[] arr, int l, int r) {
        // 插入排序思想，左边是已排序的，右边是待排序的，每次从待排序中的首个元素，遍历已排序的序列，然后插入---扑克牌
        for(int i = 1; i< arr.length; i ++){
            int j = 0;
            while(j < i && arr[j] <= arr[i]) j ++;
            // 此时j就是需要插入的位置
            int temp = arr[i];
            // 先移动, 注意移动需要从后往前移动
            for(int k = i; k > j;k--){
                arr[k] = arr[k-1];
            }
            arr[j] = temp;
        }
    }

    // 插入排序--- 在sortedSet中逆序查找，进行反冒泡
    private void insertionSortV2(int[] arr, int l, int r) {
       for(int i = l + 1; i <= r; i++){
           int j = i;
           // 这里每次都需要判定j>0，在stl中是每次比较都需要细扣
           // 我们可以通过后面一个判断来避免每次都判断j>0
           // 我们就可以把最小的值先放在最左边，这样不必遍历每个元素时，都需要判断j>0
           while(j > 0 && arr[j] < arr[j-1]){
               swap(arr,j,j-1);
               j --;
           }
       }
    }

    // 插入排序--- unguarded 无监督排序，就是为了将j>0的判断去掉
    public void _unguarded_insertionSort(int[] arr, int l, int r){
        System.out.println("_unguarded_insertionSort before arr: " + Arrays.toString(arr));
        // 先找出最小值并插入到最前面
        int minInd = l;
        for (int i = l + 1 ; i <= r; i ++) {
            if(arr[minInd] > arr[i]){
                minInd = i;
            }
        }
        // 这种整体移动也可以转换为swap
//        swap(arr, minInd, 0);
//        for(int i = minInd; i > l + 1; i--){
//            arr[i] = arr[i - 1];
//        }

        // 用swap 将最小的swap到最前面
        while(minInd > l){
            swap(arr, minInd, minInd - 1);
            minInd --;
        }
        // 然后就可以无监督了
        for(int i = l + 2; i <= r; i++){
            int j = i;
            while(arr[j] < arr[j-1]){
                swap(arr,j,j-1);
                j--;
            }
        }
    }



    // 三点取中方法1：不见得会好
    public int mid_math(int a, int b, int c){
        int s1 = a, s2 = b;
        if(a > c) s1 = c;
        if(b > c) s2 = c;
        return s1 == s2 ? Math.min(a, b) : Math.max(s1, s2);
    }

    // 三点取中方法2：用swap
    public int mid(int a, int b, int c){
        int temp = a;
        if(a > c){
            temp  = a;
            a = c;
            c = temp;
        }
        if(a > b){
            temp = a;
            a = b;
            b= temp;
        }
        if(b > c){
            temp = b;
            b = c;
            c = temp;
        }
        return b;
    }


    // 这里的做法顺便进行了一次排序
    public int mid(int[] arr, int l, int r){
        int midInd = (l + r) >> 1;
        if(arr[l] > arr[midInd]) swap(arr, l, midInd);
        if(arr[l] > arr[r]) swap(arr,l, r);
        if(arr[midInd] > arr[r]) swap(arr, midInd, r);
        return arr[midInd];
    }

    // swap 方法1：使用temp变量
    public void swap_var(int[] arr, int ai, int bi){
        int temp = arr[ai];
        arr[ai] = arr[bi];
        arr[bi] = temp;
    }

    // swap 方法2：使用异或 特别重要的一个条件时两者不能相等，否则为0
    public void swap(int[] arr, int ai, int bi){
        if(arr[ai] == arr[bi]) return;
        arr[ai] ^= arr[bi];
        arr[bi] ^= arr[ai];
        arr[ai] ^= arr[bi];
    }

}
