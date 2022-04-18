package com.avp42.datastructure.chapter9_string_match.exercise;

import com.sun.org.apache.xpath.internal.operations.String;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 哈夫曼编码算法，最优的变长编码算法， 因为其平均编码长度是最小的，用于求解使得L=∑li*pi最小的一组li
 * @author wufc@viomi.com.cn
 * @since 2022/4/9 0009
 */
public class HalfmanCoding {
    class Node{
        char c = '0';
        int frequency;
        Node left, right;
        Node(char c, int frequency){
            this.frequency = frequency;
        }
        Node(int frequency, Node left, Node right){
            this.frequency = frequency;
            this.left = left;
            this.right = right;

        }
    }

    Node root;

    HalfmanCoding(char[] chars, int[] frequencies) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(x ->x.frequency));
        for(int i = 0; i< chars.length; i ++){
            pq.offer(new Node(chars[i], frequencies[i]));
        }
        while(pq.size() > 1){
            Node node1 = pq.poll();
            Node node2 = pq.poll();
            pq.offer(new Node(node1.frequency + node2.frequency, node1, node2));
        }
        root = pq.poll();
    }

    public void output(){
        StringBuilder sb = new StringBuilder();
        _output(root, sb);
    }

    public void _output(Node root, StringBuilder buffer){
        if(root == null) return;
        if(root.c != '0'){
            System.out.println(root.c +"编码为：" +buffer.toString());
        }
        buffer.append('0');
        _output(root.left, buffer);
        buffer.deleteCharAt(buffer.length() - 1);
        buffer.append('1');
        _output(root.right, buffer);
        buffer.deleteCharAt(buffer.length() - 1);
    }


}
