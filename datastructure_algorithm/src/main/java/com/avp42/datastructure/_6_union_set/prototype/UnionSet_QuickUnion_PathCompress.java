package com.avp42.datastructure._6_union_set.prototype;

/**
 * @description: 并查集 基于路径压缩的quick union. 一般只是用这种优化策略，而不需要在加上基于权重，由于路径压缩的存在，树不会因为merge导致的很高，也就是find操作有一种降低树高的作用。
 * @author: wufc@viomi.com.cn
 * @create: 2021-05-01 10:25
 * @since： v 3.1.0
 */
public class UnionSet_QuickUnion_PathCompress {

    int arr[];

    // 很多场景需要知道连通块的个数
    int count;

    UnionSet_QuickUnion_PathCompress(int initCapacity){
        arr = new int[initCapacity + 1];
        count = initCapacity;
        for(int i = 0; i<= initCapacity; i++){
            arr[i] = i ;
        }
    }

    int find(int ele){
        arr[ele] = arr[ele] == ele? ele : find(arr[ele]);
        return arr[ele];
    }

    void merge(int a, int b){
//        int fa = find(a), fb = find(b);
//        if(fa == fb) return;
//        arr[fa] = fb;

        // 或者更为简写点
        arr[find(a)] = find(b);
        count --;
    }

}
