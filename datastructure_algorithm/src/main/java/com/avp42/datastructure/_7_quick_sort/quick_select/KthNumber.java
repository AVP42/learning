package com.avp42.datastructure._7_quick_sort.quick_select;

import java.util.Arrays;

public class KthNumber {

    public static void main(String[] args) {
        KthNumber obj = new KthNumber();

//        int[] arr = {5};
        int[] arr = {5, 4, 2, 5, 7, 5, 9, 8};
        System.out.println(obj.kthNumber(arr, 7));
        System.out.println(obj.kthNumber2(arr, 7));
        System.out.println(obj.kthNumber3(arr, 7));

    }

    public int kthNumber(int[] arr, int k){
        int l = 0, r = arr.length - 1;
        while(l < r){
            int x = l, y = r, base = arr[l];
            while(x < y){
                while(x < y && base <= arr[y]) y --;
                arr[x] = arr[y];
                while(x < y && arr[x] <= base) x ++;
                arr[y] = arr[x];
            }
            // 还要补上这个空缺 此时必然是x == y
            System.out.println("x:" + x);
            arr[x] = base;
            if(k == x + 1) {
                return arr[x];
            }else if(k < x + 1) {
                // 第k个数在左边区域
                r = x - 1;
            }else{
                // 第k个数在右边区域
                l = x + 1;
            }
            // 这里不需要对k做缩小了，因为不是递归的方式，尾递归转化成了迭代的方式
            System.out.println(Arrays.toString(arr));
            System.out.println("[" + l + "," + r+"]");

        }
        return arr[k - 1];
    }

    public int kthNumber2(int[] arr, int k){
        int l = 0, r = arr.length - 1;
        while(l < r){
            int x = l, y = r, base = arr[l];
            while(x < y){
                while(x < y && base <= arr[y]) y --;
                arr[x] = arr[y];
                while(x < y && arr[x] <= base) x ++;
                arr[y] = arr[x];
            }
            // 还要补上这个空缺
            arr[x] = base;
           // 这种判断与上面的是一样的, 这样判断，如果k == x + 1(因为x是下标), l = x + 1, r = x - 1, 跳出循环，此时返回arr[k-1]就是要求的值
            if(k <= x + 1) r = x - 1;
            if(k >= x + 1) l = x + 1;
            // 这里不需要对k做缩小了，因为不是递归的方式，尾递归转化成了迭代的方式
        }
        return arr[k - 1];
    }

    public int kthNumber3(int[] arr, int k){
        int l = 0, r = arr.length - 1;
        while(l < r){
            int x = l, y = r, base = arr[l];
            do{
                while(base < arr[y]) y --;
                while(arr[x] < base) x ++;
                if( x <= y){
                    swap(arr, x, y);
                    x ++;
                    y --;
                }
            }while(x <= y);
            // 此时 y在x的前面,所以当k==x+1的时候还是需要继续
            if(k < x + 1) r = x - 1;
            // 而且l应该等于x,而不是x+1。
            if(k >= x + 1) l = x;
            // 这里不需要对k做缩小了，因为不是递归的方式，尾递归转化成了迭代的方式
            System.out.println(Arrays.toString(arr));
            System.out.println("[" + l + "," + r+"]");
        }
        return arr[k - 1];
    }

    private void swap(int[] arr, int l, int r){
        if(arr[l] == arr[r] ) return ;
        arr[l] ^= arr[r];
        arr[r] ^= arr[l];
        arr[l] ^= arr[r];
    }

}
