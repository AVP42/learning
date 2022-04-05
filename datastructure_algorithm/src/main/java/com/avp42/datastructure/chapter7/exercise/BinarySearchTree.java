package com.avp42.datastructure.chapter7.exercise;

/**
 * @author wufc@viomi.com.cn
 * @since 2022/4/4 0004
 */
public class BinarySearchTree {
    class Node{
        int val;
        Node left, right;
        Node(int val){
            this.val = val;
        }
    }

    Node head;

    public void insert(int val){
        head = insert0(head, val);
    }

    public Node insert0(Node root, int val){
        if(root == null) return new Node(val);
        if(val < root.val) root.left = insert0(root.left, val);
        else if(root.val < val) root.right =  insert0(root.right, val);
        return root;
    }

    public void delete(int val){
        head = delete0(head, val);
    }

    private Node delete0(Node root, int val) {
        if(root == null) return null;
        if(val < root.val) root.left = delete0(root.left, val);
        else if(root.val < val) root.right  = delete0(root.right, val);
        else{
            if(root.left == null && root.right == null){
                root = null;
            }else if(root.left == null || root.right == null){
                root = root.left == null ? root.right : root.left;
            }else{
                Node predecessor = findPredeccessor(root);
                root.val = predecessor.val;
                root.left = delete0(root.left, predecessor.val);
            }
        }
        return root;
    }


    Node findPredeccessor(Node root){
        if(root ==null) return null;
        Node node = root.left;
        while(node.right != null){
            node = node.right;
        }
        return node;
    }


}
