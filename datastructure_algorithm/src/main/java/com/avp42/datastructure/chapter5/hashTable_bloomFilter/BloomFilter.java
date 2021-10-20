package com.avp42.datastructure.chapter5.hashTable_bloomFilter;

/**
 * @description:
 * @author: wufc@viomi.com.cn
 * @create: 2021-06-18 21:15
 * @since： v 3.1.0
 */
public class BloomFilter {
    private int[] arr;

    public BloomFilter(int capacity) {
        this.arr = new int[capacity];
    }

    public void insert(String s){
        int h1 = hashFunc_1(s);
        int h2 = hashFunc_2(s);
        int h3 = hashFunc_3(s);
        arr[h1 % arr.length] = 1;
        arr[h2 % arr.length] = 1;
        arr[h3 % arr.length] = 1;
    }

    public boolean find(String s){
        int h1 = hashFunc_1(s);
        int h2 = hashFunc_2(s);
        int h3 = hashFunc_3(s);
        return arr[h1 % arr.length] == 1 && arr[h2 & arr.length] == 1 && arr[h3 % arr.length] == 1;
    }


    // BKDR hash
    // 各种hash 算法 https://www.cnblogs.com/eyeszjwang/articles/2356157.html
    int hashFunc_1(String s){
        int seed = 131, hash = 0;
        for(int i = 0; i < s.length(); i ++){
            hash = hash * seed + s.charAt(i);
        }
        return hash & 0x7fffffff;
    }

    // AP hash
    int hashFunc_2(String s){
        int hash = 0, i ;
        for(i = 0; i < s.length(); i ++){
            if((i & 1) == 0){
                hash ^= ((hash << 7) ^ s.charAt(i) ^ (hash >> 3));
            }else{
                hash ^= (~((hash << 11) ^ s.charAt(i) ^ (hash >> 5)));
            }
        }
        return hash & 0x7fffffff;
    }

    // DJB hash
    int hashFunc_3(String s){
        int hash = 5381;
        for(int i = 0; i < s.length(); i ++){
            hash += (hash << 5) + (s.charAt(i));
        }
        return hash & 0x7fffffff;
    }

}
