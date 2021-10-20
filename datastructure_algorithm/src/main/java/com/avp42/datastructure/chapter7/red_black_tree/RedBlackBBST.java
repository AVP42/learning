package com.avp42.datastructure.chapter7.red_black_tree;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.avp42.datastructure.chapter7.red_black_tree.RedBlackBBST.Node.NIL;

/**
 * @description: 红黑树
 * @author: fuchang.wu@foxmail.com
 * @date: 2021-08-14 09:15
 */
public class RedBlackBBST {
    static class Node{
        static Node NIL;
        static {
            NIL = new Node(-1, 1, null, null);
            NIL.left = NIL;
            NIL.right = NIL;
        }
        int key;
        int color; // 0 red; 1 black; 2 double black
        Node left, right;
        Node(int key, int color){
            this(key, color, NIL, NIL);
        }
        Node(int key, int color, Node left, Node right){
            this.key = key;
            this.color = color;
            this.left = left;
            this.right = right;
        }
    }

    Node root = NIL;

    public Node getNewNode(int key){
        return new Node(key, 0);
    }

    public Node insert(int key){
        root =  insert0(root, key);
        // !!! 有可能到根节点，root变成了red，所以需要将root置为黑色，保证5个条件都满足
        root.color = 1;
        return root;
    }

    public Node delete(int key){
        root = delete0(root, key);
        root.color = 1;
        return root;
    }

    private Node delete0(Node root, int key) {
        if(root == NIL) return NIL;
        if(key < root.key){
            root.left = delete0(root.left, key);
        }else if(root.key < key){
            root.right = delete0(root.right, key);
        }else{
            if(root.left == NIL || root.right == NIL){
                Node temp = root.left == NIL ? root.right : root.left;
                // 触发删除失衡
                temp.color += root.color;
                return temp;
            }else{
                Node predecessor = findPredecessor(root);
                root.key = predecessor.key;
                // 从root.left开始往下找，每次回溯都是把子树的根节点晚上返回，所以可以连接上root.left
                root.left = delete0(root.left, predecessor.key);
            }
        }
        return maintainBalance_fromDelete(root);
    }


    private Node findPredecessor(Node root) {
        Node temp = root.left;
        while(temp.right!= NIL){
            temp = temp.right;
        }
        return temp;
    }


    private Node insert0(Node root, int key){
        if(root == NIL) return getNewNode(key);
        if(root.key == key) return root;
        if(root.key < key) root.right = insert0(root.right, key);
        if(root.key > key) root.left = insert0(root.left, key);
        return maintainBalance_fromInsert(root);
    }

    private boolean hasRedChild(Node root){
        return  root.left.color == 0 || root.right.color == 0;
    }

    private Node maintainBalance_fromInsert(Node root){
        if(root == NIL) return root;
        // 插入，站在祖父节点
        int unbalancedChild = -1;
        if(root.left.color == 0 && hasRedChild(root.left)) unbalancedChild = 1;
        if(root.right.color == 0 && hasRedChild(root.right)) unbalancedChild = 2;
        if(unbalancedChild < 0) return root;
        // 肯定已经失衡，双红 + uncle 红可以提取出来
        if(root.left.color == 0 && root.right.color == 0){
            handleUncleRed(root);
            return root;
        }
        // 处理 双红 + uncle 黑的情况
        if(unbalancedChild == 1){
            if(root.left.right.color == 0){
                // LR 型，先小左旋
                root.left = leftRotate(root.left);
            }
            // 大右旋
            root = rightRotate(root);
        }else{
            if(root.right.left.color == 0){
                // RL型，先小右旋
                root.right = rightRotate(root.right);
            }
            // 大左旋
            root = leftRotate(root);
        }
        // 红色下沉或者上浮
        deliverRed(root);
        return root;
    }


    private Node maintainBalance_fromDelete(Node root) {
        // 失衡类型
        // 1. 双黑 + brother 红  => 旋转，使得brother成为newFather，改为黑色；oldFather(之前一定为黑色）改为红色 => 转换为双黑 + brother 黑
        // 2. 双黑 + brother 黑
            // 2.1 brother.left=黑， brother.right=黑 ===> x与brother 褪色， father + 黑色。father可能变成双黑，递归往上
            // 2.2 brother.同侧孩子=红  (比如brother是右侧的brother，且brother.right=红，表示RR) ===>   大左旋，me和brother变黑，newFather变成原oldFather的颜色，me褪一层黑色。
            // 2.3 brother.异侧孩子=红 (比如brother是右侧的brother，且brother.left=红，表示RL) ==> 小右旋 +  原根节点与旧根节点颜色互换。
        if(root.left.color!=2 && root.right.color!=2) return root;
        int flag;
        // 如果brother是红色
        if(hasRedChild(root)){
            // brother为红
            root.color = 0;
            if(root.left.color == 0){
                flag = 1;
                root = rightRotate(root);
            }else{
                root = leftRotate(root);
                flag = 2;
            }
            root.color = 1;
            if(flag == 1) root.right =  maintainBalance_fromDelete(root.right);
            else root.left =  maintainBalance_fromDelete(root.left);
            return root;
        }
        // brother为黑色，且其孩子也都是黑色
        if(root.left.color == 1 && !hasRedChild(root.left)
            || root.right.color == 1 && !hasRedChild(root.right)){
            root.left.color -= 1;
            root.right.color -= 1;
            root.color += 1;
            return root;
        }
        // brother为黑色，且有红色孩子
        if(root.right.color == 1){
            root.left.color = 1;
            if(root.right.left.color == 0){
                // RL型
                root.right.color = 0;
                root.right = rightRotate(root.right);
                root.right.color = 1;
            }
            // RR型
            root.right.color = root.color;
            root = leftRotate(root);
        }else{
            root.right.color = 1;
            if(root.left.right.color == 0){
                // LR型
                root.left.color = 0;
                root.left = leftRotate(root.left);
                root.left.color = 1;
            }
            // LL型
            root.left.color = root.color;
            root = rightRotate(root);
        }
        root.left.color = root.right.color = 1;
        return root;


    }


    private void deliverRed(Node root) {
        root.color = 0;
        root.left.color = 1;
        root.right.color = 1;
    }

    private void handleUncleRed(Node root) {
        root.left.color = 1;
        root.right.color = 1;
        root.color = 0;
    }


    private Node rightRotate(Node root) {
        Node temp = root.left;
        root.left = temp.right;
        temp.right = root;
        return temp;
    }

    private Node leftRotate(Node root) {
        Node temp = root.right;
        root.right = temp.left;
        temp.left = root;
        return temp;
    }

    public void output(){
        output0(root);
    }

    private void output0(Node root){
        if(root == NIL) return;
        System.out.println("key:" + root.key + " color:" + root.color + " left:" + root.left.key + " right:" + root.right.key);
        output0(root.left);
        output0(root.right);
    }


    public static void main(String[] args) {
        RedBlackBBST redBlackBBST = new RedBlackBBST();
        List<Integer> keys = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            int key = (int) (Math.random() * 10 + 1);
            keys.add(key);
            System.out.println("==========inserting:" + key);
            redBlackBBST.insert(key);
            redBlackBBST.output();
        }

        for (int i = 0; i < keys.size(); i++) {
            Collections.shuffle(keys);
            System.out.println("==========deleting:" + keys.get(0));
            redBlackBBST.delete(keys.get(0));
            redBlackBBST.output();
            keys.remove(0);
        }
    }


}
