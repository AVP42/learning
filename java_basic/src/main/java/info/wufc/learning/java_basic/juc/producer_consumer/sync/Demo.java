package info.wufc.learning.java_basic.juc.producer_consumer.sync;

import com.sun.jmx.remote.internal.ArrayQueue;
import com.sun.org.apache.bcel.internal.generic.RET;

import java.sql.Time;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author wufc@viomi.com.cn
 * @since 2022/4/10 0010
 */
public class Demo {
    static class MyQueue{
        Queue<String> queue;
        int capacity;
        MyQueue(int capacity){
            queue = new LinkedList<>();
            this.capacity = capacity;
        }

        public void offer(String product) throws InterruptedException {
            synchronized (queue){
                while(queue.size() == capacity){
                    queue.wait();
                }
                queue.offer(product);
                queue.notifyAll();
            }
        }

        public String poll() throws InterruptedException {
            synchronized (queue){
                while(queue.isEmpty()){
                    queue.wait();
                }
                String ret = queue.poll();
                queue.notifyAll();
                return ret;
            }
        }

    }

    static class Producer implements Runnable{
        MyQueue queue;
        int idx;
        Producer(MyQueue queue, int idx){
            this.queue = queue;
            this.idx = idx;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    System.out.println("producer" + idx +" offer:" + idx +"_" + i);
                    queue.offer(idx +"_" + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer implements Runnable{
        MyQueue queue;
        int idx;

        Consumer(MyQueue queue, int idx){
            this.queue = queue;
            this.idx = idx;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    String ret = queue.poll();
                    System.out.println("consumer" +idx+" poll:" + ret);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        MyQueue queue = new MyQueue(10);
        new Thread(new Producer(queue, 1)).start();
        new Thread(new Consumer(queue, 2)).start();
        new Thread(new Producer(queue, 2)).start();
        new Thread(new Consumer(queue, 1)).start();
        if(Thread.activeCount() > 1) {
            Thread.yield();
        }
    }
}
