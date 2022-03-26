package com.avp42.datastructure._5_priority_queue_and_heap;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @description: 堆实现  对应java中的是java.util.PriorityQueue
 * @author: wufc@viomi.com.cn
 * @create: 2021-04-19 20:59
 * @since： v 0.0.1
 */
public class Heap<T> {
    Object[] queue;
    Comparator<T> cmp;
    int cnt = 0;

    public Heap(int capacity, Comparator<T> cmp) {
        this.queue = new Object[capacity];
        this.cmp = cmp;
    }

    public T pop(){
        if(empty()) return null;
        Object top = queue[0];
        queue[0] = queue[cnt-1];
//        queue[cnt-1] = null;
        // cnt--注意要在siftDown前面
        cnt --;
        siftDown(0);
        return (T) top;
    }

    public void push(T t){
        if(cnt == queue.length) grow(cnt + 1);
        queue[cnt] = t;
        // 注意cnt++的顺序要在siftup前面
        cnt ++;
        siftUp(cnt - 1);
    }

    private void grow(int minCapacity) {
        int oldCapacity = queue.length;
        // 如果小于64 则双倍，否则扩容50%
        int newCapacity = oldCapacity + oldCapacity < 64?oldCapacity + 2: oldCapacity >> 1;
        queue = Arrays.copyOf(queue, newCapacity);
    }


    public boolean empty(){
        return cnt ==0;
    }

    public int size(){
        return cnt;
    }

    @SuppressWarnings("unchecked")
    public void siftDown(int ind){
        // 下沉，对比左右子树，取较大值与之交换
        // 左子树为 2*i+1 右子树为2*i+2
        // 这个判断是为了下标不越界
        while(2*ind + 1 < cnt){
            int temp = ind;
            if(cmp.compare((T)queue[temp],(T)queue[2*ind+1]) > 0) temp = 2*ind+1;
            if(2*ind+2 <cnt && cmp.compare((T)queue[temp],(T)queue[2*ind+2]) > 0) temp = 2*ind+2;
            // TODO 还有一个很重要的是，如果temp==ind，说明不需要调换了
            if(temp == ind) break;
            swap(ind, temp);
            ind = temp;
        }
    }

    public void swap(int aInd, int bInd){
        Object temp = queue[aInd];
        queue[aInd] = queue[bInd];
        queue[bInd] = temp;
    }

    public void siftUp(int ind){
        // 上升 只需要和父亲进行比较，为了得到父亲，可以通过(ind-1)/2的方式得到父节点的ind，不管是左节点还是右结点
        while( ind  > 0){
            int temp = (ind-1)>>1;
            if(cmp.compare((T)queue[temp],(T)queue[ind]) < 0) break;
            swap(ind, temp);
            ind = temp;
        }
    }

    @Override
    public String toString() {
        return "Heap{" +
                "queue=" + Arrays.toString(queue) +
                ", cnt=" + cnt +
                '}';
    }

    public static void main(String[] args) {
        int sdf = "as".compareTo("sdf");
        System.out.println(sdf);
        int a = 1,b =2;
        System.out.println(a = b);

        Heap<Integer> heap = new Heap<>(5, (x, y) -> y - x);
        heap.push(3);
        System.out.println(heap);
        heap.push(5);
        System.out.println(heap);
        heap.push(1);
        System.out.println(heap);
        heap.push(4);
        System.out.println(heap);
        heap.push(2);
        System.out.println(heap);
        heap.pop();
        System.out.println(heap);
        heap.pop();
        System.out.println(heap);
        heap.pop();
        System.out.println(heap);
        heap.pop();
        System.out.println(heap);
        heap.pop();
        System.out.println(heap);

    }
}
