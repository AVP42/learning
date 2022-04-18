package com.avp42.datastructure.chapter10_other_algorithm.exercise;

import java.nio.channels.FileChannel;

/**
 * 树状数组
 *
 * @author wufc@viomi.com.cn
 * @since 2022/4/9 0009
 */
public class FenwickTree {
    int[] c;
    /**
     * 树状数组的最大下标
     */
    int n;

    FenwickTree(int[] nums){
        c = new int[nums.length + 1];
        n = nums.length;

        for(int i = 1; i <= n; i++){
            add(i - 1, nums[i - 1]);
        }
    }

    private int lowbit(int x){
        return x & (-x);
    }

    public void add(int oriInd, int delta){
        oriInd ++;
        while(oriInd <= n){
            c[oriInd] += delta;
            oriInd += lowbit(oriInd);
        }
    }

    public int query(int oriInd){
        oriInd ++;
        int ans = 0;
        while(oriInd > 0){
            ans += c[oriInd];
            oriInd -= lowbit(oriInd);
        }
        return ans;
    }

    public int at(int oriInd){
        return query(oriInd) - query(oriInd - 1);
    }


}
