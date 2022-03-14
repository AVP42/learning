package info.wufc.learning.java_basic.jvm.monitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author fuchang.wu@foxmail.com
 * @since 2022-02-20 14:03
 */
public class ThreadMonitor {

    /**
     * 死循环导致等待
     */
    public static void createBusyThread(){
        new Thread(() -> {while (true) {

        }},"testBusyThread").start();
    }


    /**
     * 线程锁等待
     */
    public static void createLockThread(final Object lock){
        new Thread(() -> {
            synchronized (lock){
                try{
                    lock.wait();

                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        },"testLockThread").start();
    }

    /**
     * 死锁当代
     */
    static class SynAddRunnable implements Runnable{
        int a, b;

        public SynAddRunnable(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public void run() {
            // 由于[-128,127]的数字会被缓存，所以Integer.valueOf(该范围的数字)返回的是同一个对象
            synchronized (Integer.valueOf(a)){
                synchronized (Integer.valueOf(b)) {
                    System.out.println(a + b);
                }
            }
        }
    }

    public static void createDeadLockThread(){
        for (int i = 0; i < 100; i++) {
            new Thread(new SynAddRunnable(1,2), "thread_" + i).start();
            new Thread(new SynAddRunnable(2, 1), "thread2_"+ i).start();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        reader.readLine();
        createBusyThread();
        reader.readLine();
        createLockThread(new Object());
        reader.readLine();
        createDeadLockThread();
    }
}
