package com.avp42.datastructure.chapter9_string_match;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @description: 双数组字典树
 *              不使用Node类封装，直接使用两个数组存储所有信息，包括树节点信息，分支信息，是否成词信息
 *              base数组，下标对应节点编号，值是特殊确定的值，用于确定子节点的编号  child_i = base[father_i] + i
 *              check数组，下边对应节点编号，值时子节点对应的父节点编号
 *              是否成词，通过check数组的值的正负来确定，check[child_i] = father_i (不成词）  check[child_i] = -father_i (成词)
 *
 *
 *      如果使用双数组字典树来实现动态的插入，那么在生成一个新的node时候，这个实现需要先将下面的分支对应的节点对应的下标占住(1个father_i对应的26个child_i都需要在check数组中先占据好位置)
 *                  base的值也是1 -> 27 - > 53....这样增加
 *      但是如果是将一个已知的字典树，转换成双数组字典树，只有真实存在节点，才会真的设置check[child_i] = father_i，否则这个位置可能被其他base[father_i2] + i 定位到，从而check[child_i]=father_i2
 *                  可以大规模节省空间.这种情况在查找的时候，就需要判断check[child_i] = abs(father_i)才能确定是否是这条路径下的前缀或者单词
 *                  空间压缩可以达到10倍以上
 * @author: fuchang.wu@foxmail.com
 * @date: 2021-10-24 13:10
 */
public class DoubleArrayTrie {
    int[] base = new int[200];
    int[] check = new int[200];

    int maxInd; // 数组中使用到的最大编号
    int root_i; // 根节点编号
    int branch_num = 26; // 分支数量
    public DoubleArrayTrie(){
        maxInd = 1;
        // 根节点编号从1开始
        root_i = 1;
    }

    /**
     * 确定base值
     *   尝试法，从1开始尝试，关键在于通过base[father+i] + i 得到的child_i，其对应的check[child_i]应当==father_i
     * @return
     */
    int getNewBaseVal(){
        int temp = 1; // temp 不能从1开始，由于root=1，则有base[1] + 0 = 1，导致ouput方法死循环
        boolean retry = true;
        while(retry){
            retry = false;
            temp += 1;
            for(int i = 0; i < branch_num; i ++){
                // 只需要判断是否被设置过，这样就可以避免出现错误的base，导致出现多个1个子节点有多个父亲的情况。
                if(check[temp + i] == 0) continue;
                retry = true;
                break;
            }
        }
       return temp;
    }

    /**
     * 初始化节点
     *   初始化base值，并将check[child_i]=father_i
     * @param p
     * @param i
     */
    private void initNode(int p) {
        int baseVal = getNewBaseVal();
        // 设置base[father_i]
        base[p] = baseVal;
        // 设置check[child_i]
        for(int k = 0; k < branch_num; k ++) {
            check[base[p] + k] = p;
            // 判断使用了多少个下标，用于衡量使用了多少内存空间
            maxInd = Math.max(base[p] + k, maxInd);
        }
    }

    public boolean insert(String word){
        int p = root_i;
        for(int i = 0; i < word.length(); i ++){
            // 如果该节点还没初始化好
            if(base[p] == 0){
                initNode(p);
            }
            p = base[p] + (word.charAt(i)  - 'a');
        }
        // check数组主要用于生成base特殊值时，避免重复; 以及存储是否成词标识
        if(check[p] < 0) return false;
        check[p] *= -1;
        return true;
    }


    public boolean search(String word){
        int p = root_i;
        for(int i = 0;i < word.length();i ++){
            int child_i = base[p] + (word.charAt(i) - 'a');
            // 根据check[child_i]来判断.
            // 如果是非压缩模式，才有可能通过base[child_i] == 0来判断，因为非压缩模式，在生成base的时候，所有的check[child_i]=father_i成立。
            if(Math.abs(check[child_i]) != p) return false;
            p = child_i;
        }
        return check[p] < 0;
    }


    public void output(){
        output0(root_i, 0, new StringBuffer());
    }

    private void output0(int root_i, int father_i, StringBuffer buffer){
        // 如果当前节点都还不存在，直接跳过
        if(base[root_i] == 0) return;
        // 如果这个节点的父亲不是这个father_i，跳过
        if(Math.abs(check[root_i]) != father_i) return;
        // 满足父亲以及成词，则输出   排除根节点的情况
        if(check[root_i] == - father_i && root_i != this.root_i) {
            System.out.println(buffer.toString());
        }
        for(int i = 0; i < branch_num; i ++){
            buffer.append((char)(i + 'a'));
            output0(base[root_i] + i, root_i, buffer);
            buffer.deleteCharAt(buffer.length() -1);
        }
    }


    public void outputDoubleArray(){
        System.out.println("base数组");
        System.out.println(Arrays.toString(base));
        System.out.println("check数组");
        System.out.println(Arrays.toString(check));
    }


    public static void main(String[] args) {
        DoubleArrayTrie trie = new DoubleArrayTrie();
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            int op = scanner.nextInt();
            if(op == 1){
                String word = scanner.next();
                System.out.println("插入:"+word + " 结果：" +trie.insert(word));
            }else if(op == 2){
                String word = scanner.next();
                System.out.println("查找:"+word + " 结果：" +trie.search(word));
            }else if(op == 3){
                System.out.println("按照字典序输出");
                trie.output();
            }else if(op == 4){
                trie.outputDoubleArray();
            }else if(op == 5){
                break;
            }
        }
    }

}
