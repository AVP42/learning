package info.wufc.learning.java_basic.jvm.hsdis;

/**
 * @author fuchang.wu@foxmail.com
 * @since 2022-02-20 13:23
 */
public class VolatileCompile {
    private static volatile boolean initFlag = false;
    private static int counter = 0;

    public static void refresh(){
        System.out.println("refresh data");
        initFlag = true;
        System.out.println("refresh data end");
    }

    public static void main(String[] args) throws InterruptedException {
        // -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly -Xcomp -XX:CompileCommand=compileonly,*VolatileCompile.refresh
        new Thread(() -> {
            while(!initFlag){
                counter++;
            }
            System.out.println("receive notice");
        }, "thread_1").start();

        Thread.sleep(3000);

        new Thread(() -> {
           refresh();
        }, "thread_2").start();

        Thread.sleep(3000);

    }


}
