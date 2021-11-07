package com.avp42.datastructure.chapter10_halfman_coding;


import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @description: 哈夫曼编码   ---  将出现频率最小的两个节点合并成一个节点，逐步构建一个二叉字典树，可以证明其平均查询长度是最小的。
 * @author: fuchang.wu@foxmail.com
 * @date: 2021-11-07 18:02
 */
public class HalfmanCoding {
    class Node{
        char c;
        int frequency;
        Node lChild, rChild;

        Node(char c, int frequency){
            this.c = c;
            this.frequency = frequency;
        }
        Node(char c, int frequency, Node lChild, Node rChild){
            this.c= c;
            this.frequency = frequency;
            this.lChild = lChild;
            this.rChild = rChild;
        }
    }

    PriorityQueue<Node> pq = new PriorityQueue<>(128, Comparator.comparingInt(x -> x.frequency));

    /** 编码，返回二叉字典树的根节点*/
    public Node encode(char[] chars, int[] frequencies){
        for(int i = 0 ; i < chars.length; i ++){
            pq.add(new Node(chars[i], frequencies[i]));
        }
        // 每次从优先队列中拿出频率最小的两个节点,构建新的节点
        while(pq.size() > 1){
            Node first = pq.poll();
            Node second = pq.poll();
            pq.add(new Node((char)0, first.frequency + second.frequency, first, second));
        }
        return pq.poll();
    }


    private void outputBinaryTrie(Node root) {
        output0(root, new StringBuffer());
    }

    private void output0(Node root, StringBuffer buffer) {
        if(root == null) return;
        if(root.c != 0){
            System.out.println("c:" + root.c + " frequency:" + root.frequency + " code:" + buffer.toString());
        }
        buffer.append('0');
        output0(root.lChild, buffer);
        buffer.deleteCharAt(buffer.length() -1);
        buffer.append('1');
        output0(root.rChild, buffer);
        buffer.deleteCharAt(buffer.length() -1);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n =scanner.nextInt();
        char[] chars = new char[n];
        int[] frequencies = new int[n];
        while(n -- > 0){
            chars[n] = scanner.next().charAt(0);
            frequencies[n] = scanner.nextInt();
        }
        scanner.close();
        // 进行哈夫曼编码
        HalfmanCoding halfmanCoding = new HalfmanCoding();
        Node root = halfmanCoding.encode(chars, frequencies);
        // 输出，可以看出频率越高，路径越短，就是这样才使得平均访问长度是最短的
//        c:a frequency:999 code:0
//        c:e frequency:10 code:1000
//        c:b frequency:111 code:1001
//        c:c frequency:222 code:101
//        c:d frequency:898 code:11
        halfmanCoding.outputBinaryTrie(root);
    }

}
