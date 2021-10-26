package com.avp42.datastructure.chapter9_string_match;

import java.util.Scanner;

/**
 * @description: 字典树
 *          每个节点不直接存储node对象的指针，将这些指针都放到一个数组中存储
 *          节点中存储数组中下标，从下标找到对应的node节点。
 * @author: fuchang.wu@foxmail.com
 * @date: 2021-10-24 13:10
 */
public class SingleArrayTrie {
    static class Node{
        boolean flag;
        int[] next = new int[26];
    }

    Node[] nodes = new Node[10000]; // 简单实现成初始化成较大的节点池
    int cnt; // 节点池中实际使用的节点数量
    Node root;
    public SingleArrayTrie(){
        root = new Node();
        nodes[1] = root; // 0不使用，从1开始用  0代表不存在
        cnt = 2; // 普通节点从2开始
    }

    /**
     * 插入
     * @param word
     * @return
     */
    public boolean insert(String word){
        Node p = root;
        for(int i = 0; i < word.length();i ++){
            int ind = word.charAt(i) - 'a';
            if(p.next[ind] == 0) p.next[ind] = getNewNode();
            p = nodes[p.next[ind]];
        }
        if(p.flag) return false;
        return p.flag = true;
    }

    private int getNewNode(){
        Node newNode = new Node();
        nodes[cnt] = newNode;
        return cnt++;
    }

    public boolean search(String word){
        Node p = root;
        for(int i =0; i < word.length(); i ++){
            int ind = word.charAt(i) - 'a';
            if(p.next[ind] == 0) return false;
            p = nodes[p.next[ind]];
        }
        return p.flag;
    }

    public void output(){
        output0(root, new StringBuffer());
    }



    private void output0(Node root, StringBuffer buffer){
        if(root == null) return;
        if(root.flag){
            System.out.println(buffer.toString());
        }
        for(int i = 0; i < root.next.length; i ++){
            buffer.append((char)(i + 'a'));
            output0(nodes[root.next[i]], buffer);
            buffer.deleteCharAt(buffer.length() - 1);
        }
    }


    public static void main(String[] args) {
        SingleArrayTrie trie = new SingleArrayTrie();
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            int op = scanner.nextInt();
            if(op == 1){
                String word = scanner.next();
                System.out.println("插入:"+word + " 结果：" +trie.insert(word));
            }else if(op == 2){
                String word = scanner.next();
                System.out.println("查找:"+word + " 结果：" +trie.search(word));
            }else if(op == 3){
                System.out.println("按照字典序输出");
                trie.output();
            }else if(op == 4){
                break;
            }
        }
    }

}
