package com.avp42.datastructure.chapter5.hashTable_bloomFilter;

import java.util.TreeSet;

/**
 * @description: 公共移除区
 * @author: wufc@viomi.com.cn
 * @create: 2021-06-17 21:45
 * @since： v 3.1.0
 */
public class HashTable_2 {

    private String[] arr;
    private int cnt;
    private TreeSet<String> buffer;
    // 使用标志位标识某个位置是否设置过值，避免为null的情况也可能是因为后面才删除的，之前的还在buffer
    private boolean[] flag;

    HashTable_2(int capacity){
        arr = new String[capacity];
        buffer = new TreeSet<>();
        flag = new boolean[capacity];
    }

    public void insert(String s){
        int h = hashFunc(s);
        int pos = h % arr.length;
        if(flag[pos]){
            cnt ++;
            flag[pos] = true;
            arr[pos] = s;
            if(cnt * 100 > arr.length * 75){
                expand();
            }
        }else if(!arr[pos].equals(s)){
            buffer.add(s);
        }

    }


    public boolean find(String s){
        int h = hashFunc(s);
        int pos = h % arr.length;
        if(!flag[pos]) return false;
        if(arr[pos].equals(s)) return true;
        return buffer.contains(s);
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
        TreeSet<String> oldBuffer = buffer;
        arr = new String[arr.length * 2];
        cnt = 0;
        flag = new boolean[arr.length];
        buffer = new TreeSet<>();
        int pos;
        // arr中的元素
        for(String s: oldArr){
            if(s != null){
                this.insert(s);
            }
        }
        // treeSet中的元素
        for(String s: oldBuffer){
            this.insert(s);
        }
    }




}
