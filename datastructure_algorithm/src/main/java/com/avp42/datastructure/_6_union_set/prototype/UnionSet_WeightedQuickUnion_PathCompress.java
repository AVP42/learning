package com.avp42.datastructure._6_union_set.prototype;

/**
 * @description: 并查集 基于权重且带路径压缩的quick union
 * @author: wufc@viomi.com.cn
 * @create: 2021-05-01 10:19
 * @since： v 3.1.0
 */
public class UnionSet_WeightedQuickUnion_PathCompress {

    int arr[];
    int size[];

    UnionSet_WeightedQuickUnion_PathCompress(int initCapacity){
        arr = new int[initCapacity + 1];
        size = new int[initCapacity + 1];

        for(int i = 0;i <= initCapacity; i ++){
            arr[i] = i;
            size[i] = 1;
        }
    }

    int find(int ele){
        // 路径压缩，找到ele的根节点，直接将其挂在根节点下面
        arr[ele] = arr[ele] == ele ? ele : find(arr[ele]);
        return arr[ele];
    }

    void merge(int a, int b){
        int fa = find(a), fb = find(b);
        // 需要改判断，否则数量不对
        if(fa == fb) return;
        if(size[fa] < size[fb]){
            arr[fa] = fb;
            size[fb] += size[fa];
        }else{
            arr[fb] = fa;
            size[fa] += size[fb];
        }
    }

}
