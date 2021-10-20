package com.avp42.datastructure._5_priority_queue_and_heap;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @description:
 * @author: wufc@viomi.com.cn
 * @create: 2021-05-01 15:41
 * @since： v 3.1.0
 */
public class Heap2 {
    int[] arr;
    Comparator<Integer> cmp;
    int cnt;

    Heap2(int capacity, Comparator<Integer> cmp) {
        arr = new int[capacity];
        this.cmp = cmp;
    }

    void offer(int ele) {
        arr[cnt++] = ele;
        siftUp();
    }

    int poll() {
        int ans = arr[0];
        arr[0] = arr[--cnt];
        siftDown();
        return ans;
    }

    void siftUp() {
        int ind = cnt - 1;
        int fatherInd;
        while ((fatherInd = (ind - 1) >> 1) >= 0) {
            // 不需要关心cmp里面是什么逻辑，只需要知道返回-1，0，1分别表示negative，zero，positive，说明前者(优先级) 低于，等于，高于后者。 < 符合常规的左小右大的方式
            // compare返回less than，左边的优先级更高
            if (cmp.compare(arr[fatherInd], arr[ind]) < 0) break;
            swap(arr, ind, fatherInd);
            ind = fatherInd;
        }
    }

    void siftDown() {
        int ind = 0;
        int child;
        while ((child = 2 * ind + 1) < cnt) {
            // 这种方式是不行的，左右子树可能只存在一个
            // int maxInd = cmp.compare(arr[2*ind+1],arr[2*ind+2]) < 0? 2 * ind +2: 2*ind +1;
            int maxInd = ind;
            if (cmp.compare(arr[child], arr[maxInd]) < 0) maxInd = child;
            child = 2 * ind + 2;
            if (child < cnt && cmp.compare(arr[child], arr[maxInd]) < 0) maxInd = child;
            if (maxInd == ind) break;
            swap(arr, ind, maxInd);
            ind = maxInd;
        }
    }

    void swap(int[] arr, int a, int b) {
        arr[a] ^= arr[b];
        arr[b] ^= arr[a];
        arr[a] ^= arr[b];
    }

    @Override
    public String toString() {
        return "Heap{" +
                "queue=" + Arrays.toString(arr) +
                ", cnt=" + cnt +
                '}';
    }

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append('a');


        Heap2 heap = new Heap2(5, (x, y) -> y - x);
        heap.offer(3);
        System.out.println(heap);
        heap.offer(5);
        System.out.println(heap);
        heap.offer(1);
        System.out.println(heap);
        heap.offer(4);
        System.out.println(heap);
        heap.offer(2);
        System.out.println(heap);
        heap.poll();
        System.out.println(heap);
        heap.poll();
        System.out.println(heap);
        heap.poll();
        System.out.println(heap);
        heap.poll();
        System.out.println(heap);
        heap.poll();
        System.out.println(heap);

    }

}
