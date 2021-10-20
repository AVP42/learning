package com.avp42.datastructure._6_union_set.prototype;

/**
 * @description: 基于权重的quick union  又称按秩优化
 *              基于权重有两种策略，基于节点数量和基于树高，从评价指标 平均查找长度可以推导出，基于节点数量是最佳的。也就是节点少的加入节点多的。

 * @author: wufc@viomi.com.cn
 * @create: 2021-05-01 09:46
 * @since： v 3.1.0
 */
public class UnionSet_WeightedQuickUnion {
    int[] arr;
    // 节点所在集合的数量，仅仅更新根节点的数量
    int[] size;

    UnionSet_WeightedQuickUnion(int initCapacity){
        arr = new int[initCapacity + 1];
        size = new int[initCapacity + 1];
        for(int i=0;i <= initCapacity; i++){
            arr[i] = i;
            size[i] = 1;
        }
    }

    int find(int ele){
        return arr[ele] == ele?ele:find(arr[ele]);
    }

    void merge(int a, int b){
        int fa = find(a), fb = find(b);
        // 必须需要这个否则，虽然不对
        if(fa == fb) return;
        if(size[fa] < size[fb]){
            arr[fa]=fb;
            size[fb] += size[fa];
        }else{
            arr[fb] = fa;
            size[fa] += size[fb];
        }
    }
}
