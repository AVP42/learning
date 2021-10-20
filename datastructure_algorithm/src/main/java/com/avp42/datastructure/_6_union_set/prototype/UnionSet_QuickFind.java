package com.avp42.datastructure._6_union_set.prototype;

/**
 * @description: 并查集，quick find  也叫染色法
 *              查找很快，但是合并过程很慢
 * @author: wufc@viomi.com.cn
 * @create: 2021-05-01 09:22
 * @since： v 3.1.0
 */
public class UnionSet_QuickFind {

    /** 数组记录每个元素的颜色*/
    int[] arr;

    UnionSet_QuickFind(int initialCapacity){
        // 元素是从1 到 n，所以使用 initCapacity + 1，方便处理边界
        arr = new int[initialCapacity  + 1];
        // 初始化的时候，各自给与不同的颜色
        for(int i= 0; i <= initialCapacity; i++){
            arr[i] = i;
        }
    }

    int find(int ele){
        // 查找过程，相当于找出所在集合的颜色
        return arr[ele];
    }

    void merge(int a, int b){
        int fa = find(a), fb = find(b);
        if(fa == fb) return;
        // 如果颜色不同，那么就需要 对某个一个集合的所有元素进行染色
        for(int i = 0; i < arr.length; i ++){
            if(arr[i] == fa) arr[i] = fb;
        }
    }

}
