package info.wufc.learning.java_basic.jvm.oom.gc;

/**
 * 利用两次标记过程中触发finalize方法逃离被回收的命运
 *
 * @author fuchang.wu@foxmail.com
 * @since 2022-02-12 12:02
 */
public class FinalizeEscapeGC {
    // 使用类变量，这是一种GC Root，来建立新的引用链，避免被GC
    public static FinalizeEscapeGC SAVE_HOOK = null;

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("FinalizeEscapeGC.finalize executed");
        SAVE_HOOK = this;
    }

    public void alive(){
        System.out.println("I'm alive!");
    }


    public static void main(String[] args) throws InterruptedException {
        SAVE_HOOK = new FinalizeEscapeGC();
        // 第一次成功拯救
        SAVE_HOOK = null;
        System.gc();
        // finalizer线程优先级很低，暂停500毫秒
        Thread.sleep(500);
        if(SAVE_HOOK!=null){
            SAVE_HOOK.alive();
        }else{
            System.out.println("I'm dead!");
        }

        // 第二次由于已经执行过finalize方法，不会再次执行了
        SAVE_HOOK = null;
        System.gc();
        Thread.sleep(500);
        if(SAVE_HOOK!=null){
            SAVE_HOOK.alive();
        }else{
            System.out.println("I'm  dead!");
        }
    }

}
