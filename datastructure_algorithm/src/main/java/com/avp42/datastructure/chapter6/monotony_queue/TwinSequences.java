package com.avp42.datastructure.chapter6.monotony_queue;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @description: oj.haizeix.com #372 双生队列
 * @author: wufc@viomi.com.cn
 * @create: 2021-07-06 20:26
 * @since： v 3.1.0
 */
public class TwinSequences {
    public int isTwinSequences(int[] a, int[] b){
        // 两个序列任意位置的RMQ都相同，相当于每个位置下的单调序列中的个数都相同
        Deque<Integer> q1 = new LinkedList<>();
        Deque<Integer> q2 = new LinkedList<>();
        int p;
        for(p = 0; p < a.length; p ++){
            while(!q1.isEmpty() && q1.peekLast() > a[p]) q1.pollLast();
            q1.offer(a[p]);
            while(!q2.isEmpty() && q2.peekLast() > b[p]) q2.pollLast();
            q2.offer(b[p]);
            if(q1.size() != q2.size()) break;
        }
        return p;
    }
}
