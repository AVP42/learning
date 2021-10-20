package com.avp42.datastructure._5_priority_queue_and_heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @description:
 * @author: wufc@viomi.com.cn
 * @create: 2021-04-19 22:31
 * @since： v 3.1.0
 */
public class LeastK {
    public static  int[] getLeastNumbers(int[] arr, int k) {
        if(arr == null) return null;
        // 大顶堆的comparator   结果大于0，为优先级更高的，y大于x，所以应当返回y-x才是大于0。
        // 可以看成默认一般来说comparator （x,y） 如果是(左)x<y（右）的，返回正数，负数表示否定
        PriorityQueue<Integer> heap = new PriorityQueue<>(k,(x, y)->y-x);
        for(int a :arr){
            if(heap.size() < k) {
                heap.add(a);
            }
            else if(a < heap.peek()) {
                // 使用heap.poll() poll是queue的接口，类似与pop
//                heap.remove(heap.peek());
                heap.poll();
                // 使用offer，offer也是queue的接口，类似与push或者add
                heap.offer(a);
            }
        }
        int[] ans = new int[k];
//        int i =0;
//        for(int b : heap){
//            ans[i++] = b;
//        }

        for(int i = 0; i < k; i++){
            ans[i] = heap.poll();
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] leastNumbers = getLeastNumbers(new int[]{2, 3, 1}, 2);
        System.out.println(Arrays.toString(leastNumbers));

    }

}
