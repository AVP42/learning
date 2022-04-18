package com.avp42.datastructure.chapter9_string_match.exercise;

import java.util.Scanner;

/**
 * 字典树-解决字符串查找与排序问题
 *
 * @author wufc@viomi.com.cn
 * @since 2022/4/9 0009
 */
public class Trie {
    class Node{
        boolean flag;
        Node[] next = new Node[26];
    }

    Node root = new Node();

    public boolean insert(String s){
        Node p = root;
        for(int i = 0;i <s.length(); i++){
            int ind = s.charAt(i) - 'a';
            if(p.next[ind] == null) p.next[ind] = new Node();
            p = p.next[ind];
        }
        if(p.flag) return false;
        p.flag = true;
        return true;
    }

    public boolean search(String s){
        Node p= root;
        for(int i= 0; i <s.length(); i++){
            int ind = s.charAt(i) - 'a';
            if(p.next[ind] == null) return false;
            p = p.next[ind];
        }
        return p.flag;
    }

    public void outputInNaturalOrder(){
        StringBuilder sb = new StringBuilder();
        _output(root, sb);
    }

    private void _output(Node root, StringBuilder buffer){
        if(root ==null) return;
        if(root.flag){
            System.out.println(buffer.toString());
        }
        for(int i = 0; i < 26; i ++){
            buffer.append(i + 'a');
            _output(root.next[i], buffer);
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
                trie.outputInNaturalOrder();
            }else if(op == 4){
                break;
            }
        }
    }

}
