package com.avp42.datastructure._7_quick_sort.exercise;

import java.util.Arrays;

public class Sorting {

    public static void main(String[] args) {
        Sorting sorting = new Sorting();
        System.out.println("--------quickSort1");
        int[] arr = {5, 4, 5, 2, 7, 5, 9, 8};
        sorting.quickSort1(arr, 0, arr.length - 1);
        System.out.println("--------quickSort2");
        int[] arr2 = {5, 4, 5, 2, 7, 5, 9, 8};
        sorting.quickSort2(arr2, 0, arr.length - 1);
        System.out.println("--------quickSort3");
        int[] arr3 = {5, 4, 5, 2, 7, 5, 9, 8};
        sorting.quickSort3(arr3, 0, arr.length - 1);
        System.out.println("--------insertionSort");
        int[] arr4 = {5, 4, 5, 2, 7, 5, 9, 8};
        sorting.insertionSort1(arr4, 0, arr.length - 1);
        System.out.println("--------insertionSort2");
        int[] arr5 = {5, 4, 5, 2, 7, 5, 9, 8};
        sorting.insertionSort2(arr5, 0, arr.length - 1);
        System.out.println("--------insertionSort3");
        int[] arr6 = {5, 4, 5, 2, 7, 5, 9, 8};
        sorting.insertionSort3(arr6, 0, arr.length - 1);

    }

    /**
     * 快速排序：两个递归
     * 将最左元素作为base元素，这样最左边的位置x就空缺出来，
     * 右侧指针遍历，发现位置y的元素小于或者等于base的，放到空缺的位置上，这样当前位置(y)就空缺出来了
     * 左侧指针遍历，发现位置x+m的元素大于base的，，就交换到新空缺出来的位置（y）
     *  以此循环
     *  ！！ 快速排序每一次分区只能使得右边的元素总是不小于左边的元素，不能就是等于base的元素都是连续排在base周边的
     *  考察5 4 5 2 7 5 9 8
     *  每次一次分区之后是这样的
     * [2, 4, 5, 5, 7, 5, 9, 8]
     * [2, 4, 5, 5, 7, 5, 9, 8]
     * [2, 4, 5, 5, 7, 5, 9, 8]
     * [2, 4, 5, 5, 5, 7, 9, 8]
     * [2, 4, 5, 5, 5, 7, 8, 9]
     */
    public void quickSort1(int[] arr, int l, int r){
        if(l >= r) return;
        // 取最左边为base元素，x位置成为空缺位置
        int x = l, y = r, base = arr[l];
        // 一个循环代表一次分区的过程
        while(x < y){
            while(x < y && base <= arr[y]) y --;
            // 将大于等于base的元素放到空缺的位置上，该位置y成为空缺位置
            if(x < y) arr[x ++] = arr[y];
            while(x < y && base > arr[x]) x ++;
            // 将小于base的元素放到空缺的位置上，该位置x成为新的空缺位置
            if(x < y) arr[y--] = arr[x];
        }
        // 最后剩下一个空缺位置就是base元素坐在的位置
        // 此时x == y
        arr[x] = base;
        System.out.println(Arrays.toString(arr));
        // 递归求解子问题
        quickSort1(arr, l,  y - 1);
        quickSort1(arr, x + 1, r);
    }

    /**
     * 快速排序：消除尾递归
     */
    public void quickSort2(int[] arr, int l, int r){
        while(l < r) {
            int x = l, y = r, base = arr[l];
            while (x < y) {
                while (x < y && base <= arr[y]) y--;
                if (x < y) arr[x++] = arr[y];
                while (x < y && arr[x] < base) x++;
                if (x < y) arr[y--] = arr[x];
            }
            arr[x] = base;
            System.out.println(Arrays.toString(arr));
            quickSort2(arr, x + 1, r);
            r = y - 1;
        }
    }

    /**
     * 快速排序：双指针同时移动
     */
    public void quickSort3(int[] arr, int l, int r){
        while(l < r){
            int x = l, y = r, base = mid(arr, l, r);
            // 一个循环代表
            do{
                while(base < arr[y]) y --;
                while(base > arr[x]) x ++;
                if(x <= y){
                    swap(arr, x, y);
                    x ++;
                    y --;
                }
            }while(x <= y);
            System.out.println(Arrays.toString(arr));
            quickSort3(arr, x, r);
            r = y;
        }
    }

    private void swap(int[] arr, int x, int y) {
        if(arr[x] == arr[y]) return;
        arr[x] ^= arr[y];
        arr[y] ^= arr[x];
        arr[x] ^= arr[y];
    }

    /** 3点取中方法，额外定义两个变量，存放最小的两个元素，
     * 如果它们相等，可能是同一个元素，则要取其他两个元素的最小值，否则取两个最小元素的最大值*/
    private int mid(int[] arr, int l, int r) {
        int c = arr[(r - l) >> 1 + l];
        int a = arr[l], b = arr[r];
        if(arr[l] > c) a = c;
        if(arr[r] > c) b = c;
        return a == b ? Math.min(arr[l], arr[r]): Math.max(a, b);
    }


    /**
     *  插入排序：从sortedSet的头部开始比对，然后整体移动
     *  i: 待插入的元素下标
     *  j: 可以插入的元素位置
     */
    public void insertionSort1(int[] arr, int l, int r){
        for(int i = l + 1; i <= r; i ++){
            int j = l;
            // 从已经排序的集合中查找
            while(j < i && arr[j] <= arr[i]) j ++;
            int temp = arr[i];
            // 将插入位置后面的元素往后移动，遍历时要冲后面开始遍历。
            for(int k = i; k >= j+1; k --){
                arr[k] = arr[k-1];
            }
            // 插入指定的位置
            arr[j] = temp;
            System.out.println(Arrays.toString(arr));
        }
    }

    /**
     * 插入排序：从sortedSet的尾部开始比对，并进行反冒泡
     * i: 待插入的元素下标
     * j: 已排序集合中可以插入的位置
     */
    public void insertionSort2(int[] arr, int l, int r){
        for(int i = l + 1; i <= r; i ++){
            int j= i;
            while(j > 0 && arr[j] < arr[j-1]){
                swap(arr, j , j-1);
                j --;
            }
            System.out.println(Arrays.toString(arr));
        }

    }


    /**
     * 插入排序：无监督模式，先安排好最小的节点，然后就不需要对j>0做判断
     * @param arr
     * @param l
     * @param r
     */
    public void insertionSort3(int[] arr, int l, int r){
        System.out.println(Arrays.toString(arr));
        // 找到最小的元素所在的下标
        int k = l;
        for(int i = l+1; i <= r; i ++){
            if(arr[k] > arr[i]) k = i;
        }
        // 将最小的元素放到最前面，以反向冒泡的方式
        for(int i = k; i >= 1; i --){
            swap(arr, i, i-1);
        }
        System.out.println(Arrays.toString(arr));
        // 这样就不需要在正式的循环中判断j>0了
        for(int i = l + 2; i <= r; i++){
            int j = i;
            while(arr[j] < arr[j-1]) {
                swap(arr, j, j-1);
                j --;
            }
            System.out.println(Arrays.toString(arr));
        }

    }

    /**
     * 内省排序：模拟C++ STL库中的排序，大区间进行快速排序，小区间进行插入排序
     * @param arr
     * @param l
     * @param r
     */
    public void introSort(int[] arr, int l, int r){

    }


}
