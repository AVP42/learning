package com.avp42.datastructure._8_merge_sort.prototype;

import java.util.Arrays;

/**
 * @description: 归并排序
 * @author: wufc@viomi.com.cn
 * @create: 2021-05-25 21:38
 * @since： v 3.1.0
 */
public class MergeSort {

    public void mergeSort(int[] arr, int l, int r){
        if(l >= r) return ;
        int mid = (l + r) >> 1;
        int[] temp = new int[r - l + 1];
        mergeSort(arr,l,mid);
        mergeSort(arr, mid + 1, r);
        int k = 0, p1 = l, p2 = mid + 1;

        while(p1<=mid || p2 <=r){
            if(p2 > r || (p1 <= mid) && arr[p1] < arr[p2]){
                temp[k++] = arr[p1++];
            }else{
                temp[k++] = arr[p2++];
            }
        }
        for(int i = l; i<=r; i ++){
            arr[i] = temp[i-l];
        }
    }


    static int[] temp2;
    public void mergeSort2(int[] arr, int l, int r){
      if(l >=r) return;
      int mid = (l + r) >> 1;
      mergeSort2(arr, l, mid);
      mergeSort2(arr, mid + 1, r);
      int k = l, p1 = l, p2 = mid + 1;
      while(p1 <= mid || p2 <= r){
          if(p2 > r || p1 <= mid && arr[p1] < arr[p2]){
              temp2[k++] = arr[p1++];

          }else{
              temp2[k++] = arr[p2++];
          }
      }

      for(int i = l; i <= r; i++){
          arr[i] = temp2[i];
      }
    }

    public static void main(String[] args) {
        int[] arr = {3, 1, 6, 2, 9, 4, 0, 5, 20, 13, 52};
        new MergeSort().mergeSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));

        int[] arr2 = {3, 1, 6, 2, 9, 4, 0, 5, 20, 13, 52};
        temp2 = new int[arr2.length];
        new MergeSort().mergeSort2(arr2, 0, arr2.length - 1);
        System.out.println(Arrays.toString(arr2));


        if(false) System.out.println("11111");System.out.println(2222);

    }
}
