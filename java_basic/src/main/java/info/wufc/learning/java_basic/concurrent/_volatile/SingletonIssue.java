package info.wufc.learning.java_basic.concurrent._volatile;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import jdk.internal.org.objectweb.asm.tree.IincInsnNode;

import java.util.LinkedList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wufc@viomi.com.cn
 * @since 2022/3/6 0006
 */
public class SingletonIssue {

//    private static volatile SingletonIssue instance;
    private static  SingletonIssue instance;

    private LinkedList<Integer> q ;

    public SingletonIssue() {

    }

    {
        q = new LinkedList<>();
        for (int i = 0; i < 1; i++) {
            q.add(new Integer(i));
        }
    }

    public static SingletonIssue getInstance() {
        if(instance == null){
            synchronized (SingletonIssue.class){
                if(instance == null){
                    instance = new SingletonIssue();
                }
            }
        }
        return instance;
    }

    public static void warmUp() {
        new SingletonIssue();
    }

    public static void main(String[] args) throws InterruptedException {

        Thread[] threads = new Thread[200];
        CyclicBarrier cb = new CyclicBarrier(200);
        for (int i = 0; i < 200; i++) {
            threads[i] = new Thread(new Worker(cb));
            threads[i].start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
    }

    static class Worker implements Runnable{
        CyclicBarrier cb;

        public Worker(CyclicBarrier cb) {
            this.cb = cb;
        }

        @Override
        public void run() {
            try {
                cb.await();
                SingletonIssue instance = SingletonIssue.getInstance();
                if (instance.q.size() != 1) System.out.println(instance.q.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }



}
