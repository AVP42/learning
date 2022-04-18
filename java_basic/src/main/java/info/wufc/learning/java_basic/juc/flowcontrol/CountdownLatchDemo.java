package info.wufc.learning.java_basic.juc.flowcontrol;

import java.util.concurrent.CountDownLatch;

/**
 * @author wufc@viomi.com.cn
 * @since 2022/4/10 0010
 */
public class CountdownLatchDemo {

    static class Task implements  Runnable{
        CountDownLatch latch;
        Task(CountDownLatch latch){
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Task.run");
        }
    }

    // 使用countdownLatch实现并发测试
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(10);
        for (int i = 0; i < 5; i++) {
            new Thread(new Task(latch)).start();
        }
        for (int i = 0; i < 10; i++) {
            latch.countDown();
            System.out.println(latch.getCount());
        }
        if(Thread.activeCount() >1) Thread.yield();
    }
}
