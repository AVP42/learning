package com.avp42.datastructure._7_quick_sort.summary;

import java.util.Arrays;
import java.util.PriorityQueue;

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
        System.out.println("--------quickSort4");
        int[] arr14 = {5, 4, 5, 2, 7, 5, 9, 8};
        sorting.quickSort4(arr14, 0, arr.length - 1);
        System.out.println("--------insertionSort");
        int[] arr4 = {5, 4, 5, 2, 7, 5, 9, 8};
        sorting.insertionSort1(arr4, 0, arr.length - 1);
        System.out.println("--------insertionSort2");
        int[] arr5 = {5, 4, 5, 2, 7, 5, 9, 8};
        sorting.insertionSort2(arr5, 0, arr.length - 1);
        System.out.println("--------insertionSort3");
        int[] arr6 = {5, 4, 5, 2, 7, 5, 9, 8};
        sorting.insertionSort3(arr6, 0, arr.length - 1);
        System.out.println("--------selectionSort");
        int[] arr7 = {5, 4, 5, 2, 7, 5, 9, 8};
        sorting.selectionSort(arr7, 0, arr.length - 1);
        System.out.println("--------bubbleSort1");
        int[] arr8 = {5, 4, 5, 2, 7, 5, 9, 8};
        sorting.bubbleSort1(arr8, 0, arr.length - 1);
        System.out.println("--------bubbleSort2");
        int[] arr9 = {5, 4, 5, 2, 7, 5, 9, 8};
        sorting.bubbleSort2(arr9, 0, arr.length - 1);
        System.out.println("--------introSort");
        int[] arr10 = {5, 4, 5, 2, 7, 5, 9, 8};
        sorting.introSort(arr10, 0, arr.length - 1);
        System.out.println("--------heapSort");
        int[] arr11 = {5, 4, 5, 2, 7, 5, 9, 8};
        sorting.heapSort(arr11, 0, arr.length - 1);
        System.out.println("--------mergeSort1");
        int[] arr12 = {5, 4, 5, 2, 7, 5, 9, 8};
        sorting.mergeSort1(arr12, 0, arr.length - 1);
        System.out.println("--------mergeSort2");
        int[] arr13 = {5, 4, 5, 2, 7, 5, 9, 8};
        sorting.mergeSort2(arr13, 0, arr.length - 1);
        System.out.println("--------countSort");
        int[] arr121 = {5, 4, 5, 2, 7, 5, 9, 8};
        sorting.bucketSort(arr121, 0, arr.length - 1);
        System.out.println("--------countSort2");
        int[] arr12_2 = {5, 4, 5, 2, 7, 5, 9, 8};
        sorting.bucketSort(arr12_2, 0, arr.length - 1);
        System.out.println("--------radixSort");
        int[] arr131 = {13, 21, 11, 32, 31, 22, 21};
        sorting.radixSort(arr131, 0, arr131.length - 1);


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
            // base与arr[y]或者arr[x]的判断有没有等于号都可以（邓俊辉老师的代码上也有）
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
     * 从邓俊辉老师的代码可以确定再交换时可以不要再判断if(x<y)了
     * 但是必须两个都是等于号，才能使得x和y继续下去
     */
    public void quickSort2(int[] arr, int l, int r){
        if(l >= r) return;
        int x = l, y = r, base = arr[l];
        while(x < y){
            while(x < y && base <= arr[y]) y --;
            arr[x] = arr[y];
            while(x < y && arr[x] <= base) x ++;
            arr[y] = arr[x];
        }
        arr[x] = base;
        System.out.println(Arrays.toString(arr));
        quickSort2(arr, x + 1, r);
        quickSort2(arr, l, y - 1);
    }

    /**
     * 快速排序：消除尾递归
     */
    public void quickSort3(int[] arr, int l, int r){
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
    public void quickSort4(int[] arr, int l, int r){
        while(l < r){
            int x = l, y = r, base = mid(arr, l, r);
            // 一个循环代表一次分区
            // 选择使用do-while是考虑到减少一次判断，直接开始里面的逻辑。
            do{
                // 由于base是一定存在在arry中的，所以不用担心出界
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
        // 应该也可以与最前面的交换
        for(int i = k; i >= 1; i --){
            swap(arr, i, i-1);
        }
//        swap(arr, k, l);
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
        while(r - l > 16){
            int x = l, y = r, base = mid(arr, l, r);
            do {
                while (base < arr[y]) y--;
                while (arr[x] < base) x++;
                if (x <= y) {
                    swap(arr, x, y);
                    x++;
                    y--;
                }
            } while (x <= y);
        }
        insertionSort3(arr, 0, arr.length -1);
    }


    /**
     * 堆排序：先利用O(n)建堆，然后逐个取出来排序
     * @param arr
     * @param l
     * @param r
     */
    public void heapSort(int[] arr, int l ,  int r){
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = l; i <= r; i ++){
            pq.offer(arr[i]);
        }
        int i = l;
        while(i <= r){
            arr[i] = pq.poll();
            i ++;
        }
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 选择排序：每次从待排序中选择一个最小的，放到指定的位置
     * @param arr
     * @param l
     * @param r
     */
    public void selectionSort(int[] arr, int l, int r){
        for(int i = l; i < r; i ++){
            int least = i;
            for(int j = i + 1; j <= r; j ++){
//                不用每次都交换，只需要交换最后一次就行了
//                if(arr[i] > arr[j]) swap(arr, i, j);
                if(arr[least] > arr[j]) least = j;
            }
            swap(arr, i, least);
            System.out.println(Arrays.toString(arr));
        }
    }

    /**
     * 冒泡排序
     * @param arr
     * @param l
     * @param r
     */
    public void bubbleSort1(int[] arr, int l, int r){
        int i = r;
        while(i > l){ // 共n-1次，只要n-1个元素排好序了，那么剩下的也已经拍好了
            // 每次只需要到j<i即可
            for(int j = 0; j < i; j ++){
                if(arr[j+1] < arr[j]) swap(arr, j + 1, j);
            }
            i --;
            System.out.println(Arrays.toString(arr));
        }
    }

    /**
     * 冒泡排序优化：如果后面已经有序（没有发生过交换），就可以跳过这一段
     * @param arr
     * @param l
     * @param r
     */
    public void bubbleSort2(int[] arr, int l, int r){
        int i = r;
        while(i > l){
            // 每次都从最左边开始
            int lastExchangeIdx = l;
            for(int j = l; j < i; j ++){
                if(arr[j+1] < arr[j]) {
                    swap(arr, j+1, j);
                    lastExchangeIdx = j;
                }
            }
            i = lastExchangeIdx;
            System.out.println(Arrays.toString(arr));
        }
    }

    /**
     * 归并排序：分治思想, 每次都声明一个数组
     */
    public void mergeSort1(int[] arr, int l, int r){
        if(l >= r) return;
        int mid = (r - l) / 2  + l;
        mergeSort1(arr, l, mid);
        mergeSort1(arr, mid + 1, r);
        int[] temp = new int[r - l + 1];
        int p = l, q = mid + 1, i = 0;
        while(p <= mid || q <= r){
            if(p > mid || q <= r && arr[p] > arr[q]) temp[i++] = arr[q++];
            else temp[i++]  = arr[p ++];
        }
        System.arraycopy(temp, 0, arr, l, r - l + 1);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 归并排序，使用一个temp数组
     */
    int[] temp;
    public void mergeSort2(int[] arr, int l, int r){
        temp = new int[r - l + 1];
        _mergeSort2(arr, l, r);
    }

    private void _mergeSort2(int[] arr, int l, int r){
        if(l >= r) return;
        int mid = ((r -l ) >> 1) + l;
        _mergeSort2(arr, l, mid);
        _mergeSort2(arr, mid + 1, r);
        int p = l, q = mid + 1, k = l;
        while(p <= mid || q <= r){
            if(q > r || p <= mid && arr[p] < arr[q]) temp[k++] = arr[p ++];
            else temp[k ++] = arr[q ++];
        }
        System.arraycopy(temp, l, arr, l, r - l + 1);
        System.out.println(Arrays.toString(arr));
    }




    /**
     * 桶排序，也叫计数排序：适用于值域有限的排序。
     * 由于值域有限，就可能出现很多重复的元素。
     * 如果值域很广，需要的空间就会很大
     * 如果利用前缀和来定位，类似下面的基数排序，需要额外的数组
     */
    public void bucketSort(int[] arr, int l , int r){
        int[] cnt = new int[10];
        for(int i = l; i <= r; i ++){
            cnt[arr[i]] += 1;
        }
        for(int i = l,j = 0; i<= r; i ++){
            while(cnt[j] == 0){
                j ++;
            }
            arr[i]  = j;
            cnt[j] -= 1;
        }
        System.out.println(Arrays.toString(arr));
    }

    public void bucketSort2(int[] arr, int l , int r){
        int[] cnt = new int[10];
        for(int i = l; i <= r; i ++){
            cnt[arr[i]] += 1;
        }
        for(int i = 0, j = 0; i < 10; i ++){
            while(cnt[i] -- > 0){
                arr[j ++] = i;
            }
        }
        System.out.println(Arrays.toString(arr));
    }


    /**
     * 基数排序：基于计数排序，进行分区处理，比如可以对高16位和低16位分开处理，或者进一步细分，进一步细分需要的空间就可以少一点
     */
    public void radixSort(int[] arr, int l, int r){
        int n = r - l + 1;
        int[] temp = new int[n];
        int[] cnt = new int[65536];
        for(int i= l; i <= r;i ++){
            cnt[low(arr[i])] += 1;
        }
        for(int i = 1; i < 65535; i ++){
            cnt[i] += cnt[i-1];
        }
        for(int i = r; i >= l; i--){
            temp[--cnt[low(arr[i])]] = arr[i];
        }
        System.out.println(Arrays.toString(temp));
        // 处理高16位
        Arrays.fill(cnt, 0);
        for(int i = r; i>=l; i --){
            cnt[hi(arr[i])] += 1;
        }
        for(int i = 1; i < 65535; i ++){
            cnt[i] += cnt[i-1];
        }
        for(int i = r; i >= l; i --){
            arr[l + (--cnt[hi(temp[i])])] = temp[i];
        }
    }

    private int low(int num){
        return num & 0xffff;
    }

    private int hi(int num){
        return (num & 0xffff0000) >>> 16;
    }


}
