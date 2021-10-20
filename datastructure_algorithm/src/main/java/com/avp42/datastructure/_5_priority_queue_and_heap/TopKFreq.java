package com.avp42.datastructure._5_priority_queue_and_heap;

import java.util.*;

/**
 * @description:
 * @author: wufc@viomi.com.cn
 * @create: 2021-04-20 23:23
 * @since： v 3.1.0
 */
public class TopKFreq {
    public static  List<String> topKFrequent(String[] words, int k) {
        Map<String,Integer> map = new HashMap<>();
        Comparator<Map.Entry<String,Integer>> cmp = (x, y) -> x.getValue().equals(y.getValue())?y.getKey().compareTo(x.getKey()):(x.getValue() -y.getValue());
        PriorityQueue<Map.Entry<String,Integer>> heap = new PriorityQueue<>(k,cmp);
        for(String w : words){
            map.compute(w,(key,v) -> v==null?1:++v);
        }
        for(Map.Entry<String,Integer> entry : map.entrySet()){
            heap.offer(entry);
            if(heap.size()>k) heap.poll();
        }
        List<Map.Entry<String,Integer>> temp = new ArrayList<>();
        while(!heap.isEmpty()){
            temp.add(heap.poll());
        }
        temp.sort((x,y) -> -1 *  cmp.compare(x,y));

        List<String> ans = new ArrayList<String>();
        for(Map.Entry<String,Integer> entry : temp){
            ans.add(entry.getKey());
        }
        return ans;


    }

    public static void main(String[] args) {
        List<String> strings = topKFrequent(new String[]{"i", "love", "leetcode", "i", "love", "coding"}, 2);
        System.out.println(strings);
        int a = 1;
        Integer b = 1;
        PriorityQueue<Double> objects = new PriorityQueue<>(Comparator.comparingDouble(x -> x));
//        objects.offer(1);
    }
}
