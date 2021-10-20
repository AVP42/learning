package com.avp42.leetcode.algorithms.mentu.easteregg.binarytree;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @description:
 * @author: wufc@viomi.com.cn
 * @create: 2021-04-15 21:09
 * @since： v 3.1.0
 */
public class BuildTree {


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

    }

    public static void main(String[] args) {
        InputStream in = BuildTree.class.getClassLoader().getResourceAsStream("buildTree.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line;
        try {
            int[] preorder = null;
            int[] inorder = null;



            int i = 1;
            // 通过这种方式避免多次调用reader.readLine()
            while ((line = reader.readLine()) != null && i < 3) {
                if (i++ == 1) {
                    preorder = Stream.of(line.split(" ")).mapToInt(Integer::valueOf).toArray();
                } else {
                    inorder = Stream.of(line.split(" ")).mapToInt(Integer::valueOf).toArray();
                }
            }
            // buildTree
            TreeNode root = _buildTree(preorder, inorder, 0, 0, preorder.length);
            // postorder
            List<Integer> postorder = new ArrayList<>(preorder.length);
            _getPostorder(root,postorder);

            long ans = _generateAnswer(postorder);
            System.out.println(ans);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static long _generateAnswer(List<Integer> postorder) {
        long ans = 0;
        for (int i = 1; i <= postorder.size(); i++) {
            ans += i * postorder.get(i - 1).longValue();
        }
        return ans;
    }

    /** preIdx --前序序列中起始位置，inIdx --中序序列中起始位置*/
    static TreeNode _buildTree(int[] preorder, int[] inorder, int preIdx, int inIdx, int num) {
        if (num == 0) return null;
        int pos = inIdx;
        while(preorder[preIdx] != inorder[pos]) ++ pos;
        TreeNode node = new TreeNode(preorder[preIdx]);
        int step = pos - inIdx;
        node.left = _buildTree(preorder,inorder,preIdx + 1, inIdx, step);
        node.right = _buildTree(preorder,inorder, preIdx + step + 1, pos + 1, num - step - 1);
        return node;
    }

    static void _getPostorder(TreeNode node, List<Integer> postorder){
       if(node == null) return ;
       _getPostorder(node.left,postorder);
       _getPostorder(node.right,postorder);
        postorder.add(node.val);

    }

}
