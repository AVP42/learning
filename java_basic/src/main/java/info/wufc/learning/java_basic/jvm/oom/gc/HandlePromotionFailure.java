package info.wufc.learning.java_basic.jvm.oom.gc;

import java.util.concurrent.TimeUnit;

/**
 * 空间分配担保
 *
 * @author fuchang.wu@foxmail.com
 * @since 2022-02-11 22:41
 */
public class HandlePromotionFailure {
    private static final int _1Mb = 1024* 1024;
    private static final int _300KB = 300* 1024;


    public static void main(String[] args) throws InterruptedException {
        // -Xms20m -Xmx20m -Xmn10m  -XX:SurvivorRatio=8  -XX:+PrintGCDetails
        // -XX:+UseConcMarkSweepGC -XX:+UseSerialGC

        byte[] allocation1, allocation2, allocation3, allocation4, allocation5, allocation6, allocation7;
        allocation1 = new byte[2 * _1Mb];
        allocation2 = new byte[2 * _1Mb];
        allocation3 = new byte[2 * _1Mb];
        allocation1 = null;

        allocation4 = new byte[2 * _1Mb];
        allocation5 = new byte[2 * _1Mb];
        allocation6 = new byte[2 * _1Mb];
        allocation4 = null;
        allocation5 = null;
        allocation6 = null;
        allocation7 = new byte[2 * _1Mb];


        // GC后，from to space都是0%
        // 实现main方法等待
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(30000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        thread.join(5000);
    }
}
