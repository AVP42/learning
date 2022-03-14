package info.wufc.learning.java_basic.jvm.oom.gc;

import java.util.concurrent.TimeUnit;

/**
 * 直接分配到老年代，如果没有指定-XX:PretenureSizeThreshold=3145728，默认还是回到eden先
 *
 * @author fuchang.wu@foxmail.com
 * @since 2022-02-11 22:41
 */
public class PretenureSizeThreshold {
    private static final int _1Mb = 1024* 1024;
    private static final int _300KB = 300* 1024;


    public static void main(String[] args) throws InterruptedException {
       //-XX:+UseConcMarkSweepGC -verbose:gc  -XX:+PrintGCDetails -Xms20m -Xmx20m -Xmn10m  -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728

        byte[] obj = new byte[4 * _1Mb];

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
