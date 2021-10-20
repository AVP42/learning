package com.avp42.datastructure._5_priority_queue_and_heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * @description:
 * @author: wufc@viomi.com.cn
 * @create: 2021-04-19 23:54
 * @since： v 3.1.0
 */
public class LastStoneWeight {

    public static int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(stones.length,(x,y) -> y -x);
        for (int stone : stones) {
            heap.offer(stone);
        }

        while(heap.size() >= 6){
            Integer y = heap.poll();
            Integer x = heap.poll();
            if(!x.equals(y)) heap.offer(y - x);
        }
        String str = "";
        int[] arr = new int[heap.size()];
        int i = arr.length -1;
        while(!heap.isEmpty()){
            arr[i--]=heap.poll();
        }
        for (int stone : arr) {
            System.out.println(stone);
            str += stone;
        }
        System.out.println(str);
        return 0;
    }

    public static void main(String[] args) throws IOException {

        InputStream in = LastStoneWeight.class.getClassLoader().getResourceAsStream("_5_priority_queue/lastStoneWeight.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line = reader.readLine();
        String[] s = line.split(" ");
        int[] stones = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            stones[i] = Integer.parseInt(s[i]);
        }

        lastStoneWeight(stones);

    }

}
