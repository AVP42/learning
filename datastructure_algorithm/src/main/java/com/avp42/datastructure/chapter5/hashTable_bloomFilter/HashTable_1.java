package com.avp42.datastructure.chapter5.hashTable_bloomFilter;

/**
 * @description: 开放定址法
 * @author: wufc@viomi.com.cn
 * @create: 2021-06-17 21:45
 * @since： v 3.1.0
 */
public class HashTable_1 {

    private String[] arr;
    private int cnt;

    HashTable_1(int capacity){
        arr = new String[capacity];
    }

    public void insert(String s){
        int h = hashFunc(s);
        int pos = h % arr.length;
        pos = reCalIfNecessary(s, pos);
        if(arr[pos] == null){
            cnt ++;
            arr[pos] = s;
            if(cnt * 100 >= arr.length * 75){
                expand();
            }
        }
    }


    public boolean find(String s){
        int h = hashFunc(s);
        int pos = h % arr.length;
        pos = reCalIfNecessary(s, pos);
        return arr[pos]!=null;
    }

    private int reCalIfNecessary(String s, int pos) {
        int step = 1;
        while (arr[pos] != null && !arr[pos].equals(s)) {
            pos += step * step;
            step += 1;
        }
        return pos;
    }


    // String hash， 使用BKDR HASH
    private int hashFunc(String s) {
        int seed = 131, hash = 0;
        for(int i = 0; i < s.length(); i ++){
            char c = s.charAt(i);
            hash = hash * seed + c;
        }
        // 转成正数
        return hash & 0X7fffffff;
    }


    private void expand() {
        String[] oldArr = arr;
        arr = new String[arr.length * 2];
        // cnt 也要重置
        cnt = 0;
        for(String s: oldArr){
            if(s != null){
                this.insert(s);
            }
        }
    }




}
