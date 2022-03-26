package com.avp42.datastructure._5_priority_queue_and_heap.exercise;

import java.util.Comparator;

/**
 * 堆
 *
 * @author wufc@viomi.com.cn
 * @since 2022/3/26 0026
 */
public class Heap {

    int[] arr;
    int cnt;
    Comparator<Integer> cmp;

    Heap(int capacity, Comparator<Integer> cmp){
        arr = new int[capacity];
        this.cmp = cmp;
    }

    public void push(int val){
        if(cnt == arr.length) resize();
        arr[cnt + 1] = val;
        cnt += 1;
        shiftUp();
    }

    private void resize() {
        int newCapacity = cnt + cnt <= 64 ? cnt : (cnt >> 1);
        int[] newArr = new int[newCapacity];
        System.arraycopy(arr, 0, newArr, 0, cnt);
        arr = newArr;
    }

    public int pop(int val){
        if(isEmpty()) return -1;
        int ret = arr[0];
        arr[0] = arr[cnt -1];
        cnt --;
        shiftDown();
        return ret;
    }

    private boolean isEmpty() {
        return cnt == 0;
    }

    public void shiftDown(){
        int ind = 0;
        // 先确保左边的孩子没有越界
        while(2 * ind + 1 < cnt){
            int l = 2 *ind + 1;
            int r = 2 * ind + 2;
            int target = l;
            if(r < cnt && arr[r] > arr[l]) target = r;
            if(arr[target] < arr[ind]) break;
            swap(arr, target, ind);
            ind = target;
        }
    }

    public void shiftUp(){
        int ind = cnt -1;
        int parentIdx ;
        while((parentIdx = (ind -1) >> 1) >= 0){
            // 大顶堆
            if(cmp.compare(arr[parentIdx], arr[ind]) < 0){
                break;
            }
            swap(arr, parentIdx, ind);
            ind = parentIdx;
        }
    }

    private void swap(int[] arr, int parentIdx, int ind) {
        int temp = arr[parentIdx];
        arr[parentIdx] = arr[ind];
        arr[ind] = temp;
    }
}
