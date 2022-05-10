package com.avp42.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Scanner;

public class BuildTree {


    static class TreeNode {
        TreeNode left, right;
        int val;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * 通过前序遍历序列，但是需要做预处理，对于子节点都加上null的空节点
     * https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/
     * https://mp.weixin.qq.com/s/qGqIWM2hmL1xewygPnb3Og
     * 前序，后序，层次遍历都是可以的，都需要将null值加上去
     */
    static class Method{
        public static void main(String[] args) {
            // 5 2 null null 1 4 3
            Scanner input = new Scanner(System.in);
            int n = input.nextInt();
            LinkedList<Integer> nodes = new LinkedList<>();
            for(int i = 0; i < n ; i ++){
                String s = input.next();
                if("null".equals(s)) nodes.add(null);
                else nodes.add(Integer.parseInt(s));
            }
            TreeNode root = buildTree(nodes);
            preorder(root);
        }

        static TreeNode buildTree(LinkedList<Integer> nodes){
            if(nodes.size() == 0) return null;
            Integer first = nodes.pollFirst();
            if(first == null) return null;
            TreeNode root = new TreeNode(first);
            root.left = buildTree(nodes);
            root.right = buildTree(nodes);
            return root;
        }

        static void preorder(TreeNode root){
            if(root == null) return ;
            System.out.println(root.val);
            preorder(root.left);
            preorder(root.right);
        }
    }

    /**
     * 通过method2
     */
    static class Method2{
        public static void main(String[] args) throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String[] s = reader.readLine().split(" ");
            int n = Integer.parseInt(s[0]);
            int root = Integer.parseInt(s[1]);
            TreeNode[] tree = new TreeNode[n + 1];
            int[][] leaf = new int[n + 1][2];
            for (int i = 1; i <= n; i++) {
                String[] ss = reader.readLine().split(" ");
                int val_i = Integer.parseInt(ss[0]);
                int left_i = Integer.parseInt(ss[1]);
                int right_i = Integer.parseInt(ss[2]);
                TreeNode node = new TreeNode(val_i);
                leaf[i][0] = left_i;
                leaf[i][1] = right_i;
                tree[i] = node;
            }
            for (int i = 1; i <= n; i++) {
                int left = leaf[i][0];
                if (left != 0) {
                    tree[i].left = tree[left];
                } else {
                    tree[i].left = null;
                }
                int right = leaf[i][1];
                if (right != 0) {
                    tree[i].right = tree[right];
                } else {
                    tree[i].right = null;
                }
            }
            TreeNode head = tree[root];
        }
    }

}
