package com.avp42.datastructure._6_union_set.prototype;

/**
 * @description: 并查集，quick union。union操作快，find操作较慢。采用的是树的思想
 * @author: wufc@viomi.com.cn
 * @create: 2021-05-01 09:41
 * @since： v 3.1.0
 */
public class UnionSet_QuickUnion {

    int[] arr;

    UnionSet_QuickUnion(int initCapacity){
        arr = new int[initCapacity + 1];
        // 初始化，每个元素的father都是自己
        for(int i = 0; i <= initCapacity; i++){
            arr[i] = i;
        }
    }

    int find(int ele){
        return arr[ele] == ele ? ele : find(arr[ele]);
    }

    void merge(int a,int b){
        int fa = find(a), fb = find(b);
        if(fa == fb) return;
        // 合并两棵树即可
        arr[fa] = fb;
    }


}
