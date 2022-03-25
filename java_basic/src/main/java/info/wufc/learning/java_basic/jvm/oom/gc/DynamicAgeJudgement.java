package info.wufc.learning.java_basic.jvm.oom.gc;


import java.util.concurrent.TimeUnit;

/**
 * 动态对象年龄判断
 *
 * @author fuchang.wu@foxmail.com
 * @since 2022-02-11 22:41
 */
public class DynamicAgeJudgement {
    private static final int _1Mb = 1024* 1024;
    private static final int _300KB = 300* 1024;


    public static void main(String[] args) throws Exception {
        // -XX:+UseConcMarkSweepGC -XX:+UseSerialGC
        //  -verbose:gc  -XX:+PrintGCDetails
        // -Xms20m -Xmx20m -Xmn10m  -XX:SurvivorRatio=8 -XX:+PrintTenuringDistribution


        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[_1Mb/4];
        allocation2 = new byte[_1Mb/4];
        // allocation1+allocation2 大于survivor空间的一半
        allocation3 = new byte[4 * _1Mb];
        allocation4 = new byte[4 * _1Mb];
        allocation4 = null;
        allocation4 = new byte[4 * _1Mb];

        // GC后，from to space都是0%
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
