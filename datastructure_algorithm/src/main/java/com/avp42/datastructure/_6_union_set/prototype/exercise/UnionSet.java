package com.avp42.datastructure._6_union_set.prototype.exercise;

/**
 * @author wufc@viomi.com.cn
 * @since 2022/3/27 0027
 */
public class UnionSet {
    int[] parent;
    int[] size;

    UnionSet(int capacity){
        parent = new int[capacity +1];
        size = new int[capacity + 1];
        for(int i = 0; i <= capacity; i ++){
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int find(int a){
        return parent[a] = parent[a] == a ? a : find(parent[a]);
    }

    public void merge(int a,  int b){
        int fa = find(a), fb = find(b);
        if(fa == fb) return;
        if(size[fa] < size[fb]){
            parent[fa] = fb;
            size[fb] += size[fa];
        }else{
            parent[fb] = fa;
            size[fa] += size[fb];
        }
    }


}
