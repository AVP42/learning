package info.wufc.learning.java_basic.concurrent._volatile;

/**
 * @author wufc@viomi.com.cn
 * @since 2022/3/6 0006
 */
public class Main {

    private static volatile int  race = 0;

    public static void autoIncr(){
        race ++;
    }

    public static void main(String[] args) {
        Thread[] threads = new Thread[20];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int i1 = 0; i1 < 10000; i1++) {
                    autoIncr();
                }
            });
            threads[i].start();
        }
        while(Thread.activeCount() > 1){
            Thread.yield();
        }
        System.out.println(race);
    }
}
