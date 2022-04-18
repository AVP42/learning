package info.wufc.learning.java_basic.juc.flowcontrol;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * @author wufc@viomi.com.cn
 * @since 2022/4/10 0010
 */
public class CyclicBarrierDemo {

    static class Task implements  Runnable{
        CyclicBarrier barrier;
        Task( CyclicBarrier barrier){
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("Task.run");
        }
    }

    // 使用cyclicBarrier实现并发测试
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(5, () -> System.out.println("trigger"));
        for (int i = 0; i < 5; i++) {
            System.out.println("start thread " +i);
            new Thread(new Task(barrier)).start();
        }
        if(Thread.activeCount() >1) Thread.yield();
    }
}
