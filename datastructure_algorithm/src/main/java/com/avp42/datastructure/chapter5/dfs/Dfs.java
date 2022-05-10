package com.avp42.datastructure.chapter5.dfs;

import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wufc@viomi.com.cn
 * @since 2022/4/4 0004
 */
public class Dfs {


    public static void main(String[] args) {
        int[] arr = {2, 3, 6, 7};
        Dfs obj = new Dfs();
        System.out.println("-------------允许重复元素-----------");
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> buffer = new ArrayList<>();
        obj.dfs1(0, arr, ans, buffer);
        System.out.println(ans);
        System.out.println("-------------子序列-----------");
        List<List<Integer>> ans2 = new ArrayList<>();
        List<Integer> buffer2 = new ArrayList<>();
        obj.dfs2(0, arr, ans2, buffer2);
        System.out.println(ans2);
        System.out.println("-------------子序列2-----------");
        List<List<Integer>> ans2_2 = new ArrayList<>();
        List<Integer> buffer2_2 = new ArrayList<>();
        obj.dfs2_1(0, arr, ans2_2, buffer2_2);
        System.out.println(buffer2_2);
        System.out.println("-------------子串-----------");
        List<List<Integer>> ans3 = new ArrayList<>();
        List<Integer> buffer3 = new ArrayList<>();
        for(int i = 0; i < arr.length; i ++){
            obj.dfs3(i, arr, ans3, buffer3);
            buffer3 = new ArrayList<>();
        }
        System.out.println(ans3);


    }

    public void dfs1(int i, int[] arr, List<List<Integer>> list, List<Integer> buffer){
        if(i == arr.length) return;
        if(buffer.size() >= 4) return;
        list.add(new ArrayList<>(buffer));
        // 跳过该元素, 所以当i==arr.length的时候，实际上是没有选择到元素的，所以可以先判断i==arr.length就返回，不同于下面的
        dfs1(i+1, arr, list, buffer);
        buffer.add(arr[i]);
        dfs1(i, arr, list, buffer);
        buffer.remove(buffer.size() - 1);
    }


    public void dfs2(int i, int[] arr, List<List<Integer>> list, List<Integer> buffer){
        list.add(new ArrayList<>(buffer));
        for(int j = i; j <arr.length; j ++){
            buffer.add(arr[i]);
            dfs2(j + 1, arr, list, buffer);
            buffer.remove(buffer.size() - 1);
        }
    }

    public void dfs2_1(int i, int[] arr, List<List<Integer>> list, List<Integer> buffer){

        if(i == arr.length)
        buffer.add(arr[i]);
        dfs2(i+1, arr, list, buffer);
        buffer.remove(buffer.size() - 1);
        dfs2(i+1, arr, list, buffer);
    }


    public void dfs3(int i, int[] arr, List<List<Integer>> list, List<Integer> buffer){
        list.add(new ArrayList<>(buffer));
        if(i == arr.length) return;
        buffer.add(arr[i]);
        dfs3(i + 1, arr, list, buffer);
    }


}
