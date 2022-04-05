package com.avp42.datastructure.chapter7.exercise;

/**
 * @author wufc@viomi.com.cn
 * @since 2022/4/4 0004
 */
public class AVLBBST {

    public static final Node NIL = new Node(-1, null, null);
    static class Node{
        int val, height;
        Node left;
        Node right;
        Node(int val){
            this.val = val;
            // 初始化height为1
            height = 1;
            left = NIL;
            right = NIL;
        }

        Node(int val, Node left, Node right){
            this.val = val;
            this.height = 0;
            this.left = left;
            this.right = right;
        }

    }

    Node head = NIL;

    public void insert(int val){
        head = insert0(head, val);
    }

    public void delete(int val){
        head = delete0(head, val);
    }

    private Node insert0(Node root, int val){
        if(root == null) return new Node(val);
        if(root.val < val) root.right = insert0(root.right, val);
        else if(val < root.val) root.left = insert0(root.left, val);
        updateHeight(root);
        return maintainBalance(root);
    }


    private Node delete0(Node root, int val){
        if(root == null) return null;
        if(root.val < val) root.right = delete0(root.right, val);
        else if(val < root.val) root.left = delete0(root.left, val);
        else{
            if(root.left == null || root.right == null){
                root = root.left == null ? root.right :root.left;
            }else{
                Node predecessor = findPredeccessor(root);
                root.val = predecessor.val;
                root.left = delete0(root.left, predecessor.val);
            }
        }
        updateHeight(root);
        return maintainBalance(root);
    }

    private Node findPredeccessor(Node root){
        if(root == null) return null;
        Node left = root.left;
        while(left.right != null) left = left.right;
        return left;
    }

    private void updateHeight(Node root){
        if(root == null) return;
        root.height = Math.max(root.left.height, root.right.height) + 1;
    }

    private Node maintainBalance(Node root){
        if(root == null) return null;
        if(Math.abs(root.left.height - root.right.height) <=  1) return root;
        if(root.left.height > root.right.height){
            if(root.left.left.height < root.left.right.height){
                //LR
                root.left = leftRotate(root.left);
            }
            root = rightRotate(root);
        }else{
            if(root.right.left.height > root.right.right.height){
                root.right = rightRotate(root.right);
            }
            root = leftRotate(root);
        }
        return root;
    }

    private Node leftRotate(Node root){
        Node newParent = root.right;
        root.right = newParent.left;
        newParent.left = root;
        // 还需要更新高度
        updateHeight(root);
        updateHeight(newParent);
        return newParent;
    }

    private Node rightRotate(Node root){
        Node newParent = root.left;
        root.left = newParent.right;
        newParent.right = root;
        updateHeight(root);
        updateHeight(newParent);
        return newParent;
    }

}
