package info.wufc.learning.java_basic.jvm.oom.gc;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 引用计数算法
 *
 * @author fuchang.wu@foxmail.com
 * @since 2022-02-11 22:41
 */
public class G1GC {
    private static final int _1Mb = 1024* 1024;
    private static final int _300KB = 300* 1024;
    static class Obj{

        public Obj() {
        }

        public Obj(byte[] bigArr) {
            this.bigArr = bigArr;
        }

        // 将对象所占内存加大，以便明显看出是否被gc
        private byte[] bigArr = new byte[_300KB];
    }

    public static void main(String[] args) throws InterruptedException {
        // -XX:+PrintCommandLineFlags -XX:+PrintGCDateStamps -XX:+PrintGC  -Xmx10m -XX:+UseG1GC
        System.out.println("---------连续分配20个300K对象--------");
        Obj[] objs = new Obj[20];
        for (int i = 0; i < 20; i++) {
            objs[i] = new Obj();
        }

        System.out.println("--------释放5个300K对象，然后分配2M大对象---------");
        for (int i = 0; i < 5; i++) {
            objs[i] = null;
        }

        Obj obj = new Obj(new byte[2 * _1Mb]);

//        手动GC
//        System.gc();
//        Runtime.getRuntime().gc();

        // 实现main方法等待
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
