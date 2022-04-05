package com.avp42.datastructure.binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 中序遍历
 *
 * @author wufc@viomi.com.cn
 * @since 2022/4/4 0004
 */
public class Inorder {

    class Node{
        int val;
        Node left, right;
        Node(int val ){
            this.val = val;
        }
    }


    public void inorder_iterator(Node root){
        if(root == null) return;
        Stack<Node> stack = new Stack();
        while(!stack.isEmpty() || root !=  null){
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            // 将当前弹出来的赋给root
            root = stack.pop();
            System.out.println(root.val);
            root = root.right;
        }
    }

    public void wrong_inorder_iterator(Node root){
        if(root == null) return;
        List<Integer> ans = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            Node top = stack.peek();
            // 这会导致死循环，不能根据peek来加入，因为第二次弹出root的时候，又进入root.left的循环
            while(top.left != null){
                stack.push(top.left);
            }
            top = stack.pop();
            ans.add(top.val);
            if(top.right != null){
                stack.push(top.right);
            }
        }
    }

}
