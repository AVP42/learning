package com.avp42.datastructure._5_priority_queue_and_heap;

import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Stream;

/**
 * @author wufc@viomi.com.cn
 * @since 2022/3/27 0027
 */
public class NatureOrderHeap {
        int[] queue;
        int cnt;
        Comparator<Integer> cmp;
    NatureOrderHeap(int initK, Comparator<Integer> cmp){
            queue = new int[initK];
            this.cmp = cmp;
        }
        public void offer(int val){
            if(cnt == queue.length) resize();
            queue[cnt ++] = val;
            shiftUp();
            // print();
        }

        public int poll(){
            if(cnt == 0) return -1;
            int ret = queue[0];
            queue[0] = queue[cnt-1];
            cnt -= 1;
            shiftDown();
            return ret;
        }

        public boolean isEmpty(){
            return cnt ==0;
        }

        public void shiftUp(){
            int ind = cnt - 1;
            int parentInd;
            while((parentInd = (ind - 1) >> 1) >= 0 && cmp.compare(queue[parentInd], queue[ind]) > 0){
                swap(parentInd, ind);
                ind = parentInd;
            }
        }

        public void shiftDown(){
            int ind = 0;
            while(2 * ind + 1 < cnt){
                int l = 2 *ind + 1;
                int r = l + 1;
                int temp = l;
                // 如果r 更小，需要交换，因为默认是小顶堆（优先级小的在前面），自然排序。
                if(r < cnt && cmp.compare(queue[l], queue[r]) > 0) temp = r;
                if(cmp.compare(queue[ind], queue[temp]) > 0){
                    swap(ind, temp);
                }
                ind = temp;
            }
        }

        public void swap(int l, int r){
            int temp= queue[l];
            queue[l] = queue[r];
            queue[r] = temp;
        }

        public int size(){
            return cnt;
        }

        public void print(){
            System.out.println("----------");
            for(int i = 0; i < cnt ; i ++){
                System.out.println(queue[i]);
            }
        }



        public void resize(){
            int newCapacity = queue.length + queue.length < 64 ? queue.length + 2 : queue.length >> 1;
            int[] newArr = new int[newCapacity];
            System.arraycopy(queue, 0, newArr, 0, cnt);
        }


    public static void main(String[] args) {
        int[] ints = Stream.of(2,4).sorted(Collections.reverseOrder()).mapToInt(x -> x).toArray();
    }

}
