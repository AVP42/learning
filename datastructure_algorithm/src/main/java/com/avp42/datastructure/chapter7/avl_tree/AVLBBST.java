package com.avp42.datastructure.chapter7.avl_tree;

/**
 * @description: AVL 平衡二叉搜索树
 * @author: fuchang.wu@foxmail.com
 * @date: 2021-08-06 21:57
 */
public class AVLBBST {
    static class Node{
        int val, height;
        Node left , right ;
        Node(int val){
            this.val = val;
            height = 1;
            left = NIL;
            right = NIL;
        }

        Node(int val, Node left, Node right){
            this.val = val;
            height = 0;
            this.left = left;
            this.right = right;
        }
    }

    Node root = NIL;
    static Node NIL = new Node(-1, null, null);

    public void insert(int val){
        root = insert0(root, val);
    }

    public void delete(int val){
        root = delete0(root, val);
    }

    public Node insert0(Node node, int val){
        if(node == NIL) return new Node(val);
        if(val < node.val) node.left = insert0(node.left, val);
        else if(node.val < val) node.right = insert0(node.right, val);
        // 更新高度
        updateHeight(node);
        // 维护平衡性，组成返回维护后的节点
        return maintain(node);
    }

    public Node delete0(Node node, int val){
        if(node == NIL) return NIL;
        if(val < node.val) node.left =  delete0(node.left, val);
        else if(node.val < val) node.right = delete0(node.right, val);
        else {
            // 出度为1和出度为0的可以合并在一起
            if(node.left == NIL || node.right == NIL){
                return node.left == NIL ? node.right : node.left;
            }else{
                Node predecessor = findPredecessor(node);
                node.val = predecessor.val;
//              这里不能直接返回，因为递归里面得到的是node左子树的根节点，而我们需要返回node，一层一层去回溯
//                return delete0(node.left, predecessor.val);
                node.left = delete0(node.left, predecessor.val);
            }
        }
        // 更新高度， 本层操作
        updateHeight(node);
        // 不同层的返回方式不一样， 上面的不同返回，就像递归基的返回方式不一样，
        return maintain(node);
    }

    private void updateHeight(Node node) {
        if(node == NIL) return ;
        node.height = Math.max(node.left.height, node.right.height) + 1;
    }

    private Node maintain(Node node){
        if(node == NIL) return NIL;
//        System.out.println("node val:" + node.val + ", height:"+ node.height + ", differ:" + (node.left.height -  node.right.height));
        if(Math.abs(node.left.height -  node.right.height) > 1){
            if(node.left.height > node.right.height){
                if(node.left.left.height < node.left.right.height){
                    // LR
                    System.out.print("leftRotate >");
                    node.left = leftRotate(node.left);
                }
                // LL
                System.out.print("rightRotate >");
                return  rightRotate(node);
            }else{
                if(node.right.left.height > node.right.right.height){
                    // RL
                    System.out.print("rightRotate >");
                    node.right = rightRotate(node.right);
                }
                // RR
                System.out.print("leftRotate >");
                return   leftRotate(node);
            }
        }
        return node;
    }

    public Node leftRotate(Node node){
        Node newFather = node.right;
        node.right = newFather.left;
        newFather.left = node;
        updateHeight(node);
        updateHeight(newFather);
        return newFather;
    }

    public Node rightRotate(Node node){
        Node newFather = node.left;
        node.left = newFather.right;
        newFather.right = node;
        updateHeight(node);
        updateHeight(newFather);
        return newFather;
    }

    private Node findPredecessor(Node node) {
        if(root == NIL) return NIL;
        Node temp = node.left;
        while(temp.right != NIL) temp = temp.right;
        return temp;
    }

    public void output() {
        output0(root);
        System.out.println();
    }

    private void output0(Node node){
        if(node == NIL) return;
        System.out.print(node.val + "(" + node.height + "), ");;
        output0(node.left);
        output0(node.right);
    }

    public void outputInorder() {
        outputInorder0(root);
        System.out.println();
    }

    private void outputInorder0(Node node){
        if(node == NIL) return;
        outputInorder0(node.left);
        System.out.print(node.val + "(" + node.height + "), ");;
        outputInorder0(node.right);
    }


    public static void main(String[] args) {
        int[][] data = {{0, 5}, {0, 9}, {0, 8}, {0, 3}, {0, 2}, {0, 4}, {0, 1}, {0, 7}, {1, 5}};
        AVLBBST avl = new AVLBBST();
        for(int[] datum : data){
            if(datum[0] == 0){
                avl.insert(datum[1]);
            }else{
                avl.delete(datum[1]);
            }
            avl.output();
        }
    }
}
