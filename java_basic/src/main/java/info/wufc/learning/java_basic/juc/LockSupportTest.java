package info.wufc.learning.java_basic.juc;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {

    public static void main(String[] args) {
        System.out.println("unpark");
        LockSupport.unpark(Thread.currentThread());
        LockSupport.park(Thread.currentThread());
        System.out.println("park");
        LockSupport.park(Thread.currentThread());
        System.out.println("park again");
        ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
    }
}
