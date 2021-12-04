package com.avp42.datastructure.chapter10_other_algorithm;

/**
 * @description: 树状数组  查询区间和以及动态修改某个元素都是O(logn); 类似地，还可以用于比如
 * @author: fuchang.wu@foxmail.com
 * @date: 2021-11-13 10:27
 */
public class FenwickTree {
    int[] c; // 树状数组，c[i] = 原数组中[i-lowbit(i), i - 1]区间的和，也就是第i-1项作为起点，左侧lowbit(i)项的和。
    int maxInd; // 树状数组的最大下标

    FenwickTree(int n){
        c = new int[n+1];
        maxInd = n;
    }

    /**
     * 原数组的单点操作，相当于树状数组的区间操作   J = i + lowbit(i)
     * @param i 是原数组的下标，树状数组的下标为原数组下标 + 1
     * @param delta
     */
    public void add(int i, int delta){
        i ++;
        while(i <= maxInd) {
            c[i] += delta;
            i += lowbit(i);
        }
    }

    /**
     * 查询原数组前m项的和     S[i] = S[i-lowbit(i)] + c[i]
     * @param m
     * @return
     */
    public int query(int m){
        int ans = 0;
        while(m > 0){
            ans += c[m];
            m -= lowbit(m);
        }
        return ans;
    }

    /**
     * 查询
     * @param i
     * @return
     */
    public int at(int i){
        return query(i + 1) - query(i);
    }

    /**
     * 输出原数组，树状数组等信息
     */
    public void output(){
        for(int i = 0; i <= maxInd; i ++){
            System.out.printf("%5d", i);
        }
        System.out.println();
        for(int i = 0; i <= maxInd; i ++){
            System.out.printf("%5d", c[i]);
        }
        System.out.println();
        System.out.printf("%5d", 0);
        for(int i = 1; i <= maxInd; i ++){
            System.out.printf("%5d", query(i) - query(i - 1));
        }
        System.out.println();
        System.out.println("====================================");
    }

    /**
     * 返回二进制表示的最后一个1的位权
     * @param x
     * @return
     */
    private int lowbit(int x){
        return x & (-x);
    }


    public static void main(String[] args) {
        FenwickTree fenwickTree = new FenwickTree(10);
        fenwickTree.add(0, 2);
        fenwickTree.output();
        fenwickTree.add(5, 5);
        fenwickTree.output();
        fenwickTree.add(7, 1);
        fenwickTree.output();
        fenwickTree.add(2, 3);
        fenwickTree.output();
        fenwickTree.add(9, 9);
        fenwickTree.output();
    }

}
