package com.avp42.datastructure._7_quick_sort.exercise;

public class Sorting {

    public void bubbleSort(int[] arr, int l, int r){
        int i = r;
        while(i > l){
            int lastExchange = l;
            for(int j = l; j < i; j ++){
                if(arr[j+1] < arr[j]){
                    swap(arr, j + 1, j);
                    lastExchange = j;
                }
            }
            i = lastExchange;
        }
    }

    public void selectionSort(int[] arr, int l, int r){
        for(int i = l; i < r; i ++){
            int leastIdx = i;
            for(int j = i; j < r; j ++){
                if(arr[j] < arr[j + 1]) {
                    leastIdx = j+1;
                }
            }
            swap(arr, leastIdx, i);
        }
    }

    public void insertionSort(int[] arr, int l, int r){
        for(int i = l; i < r; i ++){
            int j = i + 1;
            while(j > 0 && arr[j+1] < arr[j]){
                swap(arr, j+1, j);
                j --;
            }
        }
    }


    public void quickSort(int[] arr, int l, int r){
        while(l < r){
            int x = l, y = r, base = mid(arr,l, r);
            do{
                while(base < arr[y]) y --;
                while(base > arr[x]) x ++;
                if (x <= y) {
                    swap(arr, x, y);
                    x ++;
                    y --;
                }
            }while(x <= y);
//            quickSort(arr, x + 1, r);
//            r = y- 1;
            quickSort(arr, x, r);
            r = y;
        }

    }

    public int mid(int[] arr, int l, int r){
        int s1 = arr[l], s2 = arr[r];
        int c = arr[(r -l) / 2  + l];
        if(arr[l] < c) s1 = c;
        if(arr[r] < c ) s2 = c;
        return s1 == s2 ? Math.min(arr[l], arr[r]) : Math.max(s1, s2);

    }


    private void swap(int[] arr, int l, int r){
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }
}
