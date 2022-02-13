package info.wufc.learning.java_basic.jvm.oom;

import java.util.concurrent.TimeUnit;

/**
 * 引用计数算法
 *
 * @author fuchang.wu@foxmail.com
 * @since 2022-02-11 22:41
 */
public class ReferenceCounterGC {
    static class Obj{
        private Object instance = null;
        private static final int _1Mb = 1024* 1024;
        // 将对象所占内存加大，以便明显看出是否被gc
        private byte[] bigArr = new byte[1* _1Mb];
    }

    public static void main(String[] args) throws InterruptedException {
        Obj A = new Obj();
        Obj B = new Obj();
        A.instance = B;
        B.instance = A;


        A = null;
        B = null;

        // 等效与Runtime.getRunTime().gc();
        // 虚拟机尽量进行gc
        // GC日志上会有GC (System.gc()) 标记
//        System.gc();
//        Runtime.getRuntime().gc();

        //
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        thread.join(5000);
    }
}
