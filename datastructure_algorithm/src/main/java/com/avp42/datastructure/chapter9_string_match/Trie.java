package com.avp42.datastructure.chapter9_string_match;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @description: 字典树
 * @author: fuchang.wu@foxmail.com
 * @date: 2021-10-24 13:10
 */
public class Trie {
    class Node{
        boolean flag;// 是否成词
        Node[] next = new Node[26];// 下属节点，数组下标代表了分支
    }

    Node root;

    public Trie(){
        root = new Node();
    }

    /**
     * 插入方法
     * @param word
     * @return 是否是第一次插入
     */
    public boolean insert(String word){
        Node p = root;
        for(int i = 0; i < word.length(); i ++){
            char c = word.charAt(i);
            int ind = c - 'a';
            if(p.next[ind] == null) p.next[ind] = new Node();
            p = p.next[ind];
        }
        if(p.flag) return false;
        p.flag = true;
        return true;
    }

    /**
     * 查找方法
     * @param word
     * @return
     */
    public boolean search(String word){
        Node p = root;
        for(int i =0; i < word.length();i ++){
            int ind = word.charAt(i) - 'a';
            if(p.next[ind] == null) return false;
            p = p.next[ind];
        }
        return p.flag;
    }

    /**
     * 按照字典排序输出方法
     */
    public void output(){
        StringBuffer buffer = new StringBuffer();
        output0(root, buffer);
    }

    private void output0(Node root, StringBuffer buffer){
        if(root == null) return;
        if(root.flag){
            System.out.println(buffer.toString());
        }
        for(int i = 0; i < root.next.length;i ++){
            buffer.append((char)(i + 'a'));
            output0(root.next[i], buffer);
            buffer.deleteCharAt(buffer.length() - 1);
        }
    }


    public static void main(String[] args) {
        Trie trie = new Trie();
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
