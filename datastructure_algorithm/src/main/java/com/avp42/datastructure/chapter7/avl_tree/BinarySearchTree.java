package com.avp42.datastructure.chapter7.avl_tree;

import com.avp42.basic.System.InputStreamReaderDemo;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @description: 二叉搜索树或者叫二叉排序树
 * @author: fuchang.wu@foxmail.com
 * @date: 2021-08-06 20:30
 */
public class BinarySearchTree {
    static class Node{
        int val;
        Node left, right;
        Node(int val){
            this.val = val;
        }
    }

    Node root;

    Node getNewNode(int val ){
        return  new Node(val);
    }

    void insert(int val){
        root =  insert0(root, val);
    }

    void delete(int val){
        root =  delete0(root, val);
    }


    private Node delete0(Node node, int val) {
        if(node == null) return null;
        if(node.val > val) {
            node.left = delete0(node.left,val);
        }
        else if(node.val < val) {
            node.right = delete0(node.right, val);
        }
        else{
            if(node.left == null && node.right == null){
                return null;
            }else if(node.left == null || node.right == null){
                return node.left == null? node.right : node.left;
            }else{
                Node predecessor = findPredecessor(node);
                node.val = predecessor.val;
//              这里不能直接返回，这里返回的是node左子树的根节点，我们需要返回本节点，最终得以返回整树的根节点
//                return delete0(node.left, predecessor.val);
                node.left =  delete0(node.left, predecessor.val);
            }
        }
        return node;
    }

    private Node findPredecessor(Node node) {
        if(node == null) return null;
        Node temp = node.left;
        // 只有两个出度才会去找前继，所以temp必然存在
        while(temp.right != null) temp = temp.right;
        return temp;
    }

    /**
     * 返回的依然是树的根节点，因为这是一个递归过程，每层都返回当前节点，最终就是返回根节点
     * 因为返回的都是当前节点
     * @param node
     * @param val
     * @return
     */
    private Node insert0(Node node, int val) {
        if(node == null) return getNewNode(val);
        // 编程技巧，这种虽然在回溯过程会已知有这个赋值操作，但是避免了繁冗的判断
        if(node.val > val) node.left = insert0(node.left, val);
        else if(node.val < val) node.right = insert0(node.right, val);
        // 递归中return 只是返回给上一层的，不是直接返回
        // 特殊的，return 递归 且只有一个return 语句，这种形式就是不需要在回溯过程中进行任何处理，直接返回，这样返回就是最底层的结果
        return node;
    }

    /**
     * 如果没有使用上面的技巧，就需要这么些
     * @param node
     * @param val
     * @return
     */
    private Node insert1(Node node, int val) {
//        这里不会出现node == null的情况了，dive 前已经判断了
//        但是底层遍历时会出现node = null;
        if(node == null) return getNewNode(val);
        if(node.val > val) {
            // 下面这个if-else 可以放到递归中，每层递归中就把node.left 赋值为上一层的返回
            // 也就相当于将左右节点的判空操作作为公共的递归基，这样对于if里面和else里面就都需要给node.left进行赋值
            // 这种赋值不会改变树的结构，因为之前是node的left节点，也是赋给node的left节点
            if(node.left == null){
                node.left = getNewNode(val);
                return node;
            }else{
                // 必须都是返回node，才能最后返回的是根节点
                insert1(node.left, val);
                return node;
            }
        }else if(node.val < val) {
            if(node.right == null){
                node.right = getNewNode(val);
                return node;
            }else{
                // 这里返回的就不是node节点了，而是下面一层返回的节点
//               return insert1(node.right, val);
                insert1(node.right, val);
                return node;
            }
        }else{
            return node;
        }

    }

    public void outputInorder() {
        System.out.println("------begin------");
        output0(root);
        System.out.println("");
    }

    private void output0(Node node) {
        if(node == null) return;
        output0(node.left);
        System.out.print(node.val + ", ");
        output0(node.right);
    }


    public static void main(String[] args) throws IOException {
//        int[][] data = {{0, 5}, {0, 1}, {0, 3}, {0, 2}, {0, 8}, {0, 4}, {1, 2}, {1, 8}, {1, 5}};
        int[][] data = {{0, 5}, {0, 9}, {0, 8}, {0, 3}, {0, 2}, {0, 4}, {0, 1}, {0, 7}, {1, 5}};
        BinarySearchTree bst = new BinarySearchTree();
        for(int[] datum : data){
            if(datum[0] == 0){
                bst.insert(datum[1]);
            }else{
                bst.delete(datum[1]);
            }
            bst.outputInorder();
        }

    }
}
