package com.avp42.datastructure.chapter5.hashTable_bloomFilter;

import java.util.TreeSet;

/**
 * @description: 拉链法
 * @author: wufc@viomi.com.cn
 * @create: 2021-06-17 21:45
 * @since： v 3.1.0
 */
public class HashTable_3 {


    static class Node{
        String val;
        Node next;
        Node(String val,Node next){
            this.val = val;
            this.next = next;
        }

        public Node(String val) {
            this.val = val;
        }
    }

    private Node[] arr;
    private int cnt;


    HashTable_3(int capacity){
        arr = new Node[capacity];
    }

    // 这里采用的新冲突在链表前面，避免需要初始化每个槽位的node, 不过最好还是使用哨兵节点好点，在处理删除的时候就不用处理太多边界
    public void insert(String s){
       int h = hashFunc(s);
       int pos = h % arr.length;
       Node p = arr[pos], q = p;
       while(q!=null && q.val.equals(s)) q = q.next;
       if(q == null){
           cnt ++;
           Node node = new Node(s);
           arr[pos] = node;
           node.next = p;
           if(cnt > arr.length * 3) expand();
       }
    }


    public boolean find(String s){
        int h = hashFunc(s);
        int pos = h % arr.length;
        Node p = arr[pos];
        while(p != null && p.val.equals(s)) p = p.next;
        return p!=null;
    }

    // String hash， 使用BKDR HASH
    private int hashFunc(String s) {
        int seed = 131, hash = 0;
        for(int i = 0; i < s.length(); i ++){
            char c = s.charAt(i);
            hash = hash * seed + c;
        }
        return hash & 0x7fffffff;
    }


    private void expand() {
        Node[] oldArr = arr;
       arr = new Node[arr.length * 2];
       cnt = 0;
       for(Node node: oldArr){
           while(node!=null){
               this.insert(node.val);
               node = node.next;
           }
       }
    }




}
