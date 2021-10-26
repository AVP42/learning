package com.avp42.datastructure.chapter9_string_match;

import java.util.Scanner;

/**
 * @description: 双数组字典树 由别的字典树压缩而来
 * @author: fuchang.wu@foxmail.com
 * @date: 2021-10-24 13:10
 */
public class CompressedDoubleArrayTrie extends DoubleArrayTrie {

    private CompressedDoubleArrayTrie(){
        super();
    }

    /**
     * 压缩(转换单数组字典树）
     * @param st
     * @return
     */
    public static CompressedDoubleArrayTrie convertFromSingleArrayTrie(SingleArrayTrie st){
        CompressedDoubleArrayTrie dat = new CompressedDoubleArrayTrie();
        convert0(dat, st, 1,  st.root);
        return dat;
    }

    int getNewBaseVal2(SingleArrayTrie.Node stRoot) {
        int temp = 1;
        boolean retry = true;
        while(retry){
            retry = false;
            temp += 1;
            for(int i = 0; i < branch_num; i ++){
                if(stRoot.next[i] == 0) continue;
                if(check[temp + i] == 0) continue;
                retry = true;
                break;
            }
        }
        return temp;
    }

    private static void convert0(CompressedDoubleArrayTrie dat, SingleArrayTrie st, int datRoot, SingleArrayTrie.Node stRoot) {
        if(stRoot == null) return;
        // 设置base值，相当于处理节点的信息
        int baseVal = dat.getNewBaseVal2(stRoot);
        dat.base[datRoot] = baseVal;
        // 设置check值，相当于在处理边的信息
        for(int i =0; i < dat.branch_num; i ++){
            // 如果原字典树没有这个分支，则跳过
            if(stRoot.next[i] == 0) continue;
            dat.check[dat.base[datRoot] + i] = datRoot;
            if(st.nodes[stRoot.next[i]].flag){
                dat.check[dat.base[datRoot] + i] = - datRoot;
            }
        }
        // 进入递归
        for(int i = 0; i < dat.branch_num; i ++){
            if(stRoot.next[i] == 0) continue;
            convert0(dat, st, dat.base[datRoot] + i, st.nodes[stRoot.next[i]]);
        }
    }

    public static void main(String[] args) {
        SingleArrayTrie st = new SingleArrayTrie();
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            int op = scanner.nextInt();
            if(op == 1){
                String word = scanner.next();
                System.out.println("插入:"+word + " 结果：" +st.insert(word));
            }else if(op == 5){
                break;
            }
        }

        // 压缩成双数组字典树
        CompressedDoubleArrayTrie cdat = CompressedDoubleArrayTrie.convertFromSingleArrayTrie(st);

        // 比较大小

        while(scanner.hasNext()){
            int op = scanner.nextInt();
            if(op == 2){
                String word = scanner.next();
                System.out.println("查找:"+word + " 结果：" + cdat.search(word));
            }else if(op == 3){
                System.out.println("按照字典序输出");
                cdat.output();
            }else if(op == 4){
                cdat.outputDoubleArray();
            }else if(op == 5){
                break;
            }
        }
    }

}
