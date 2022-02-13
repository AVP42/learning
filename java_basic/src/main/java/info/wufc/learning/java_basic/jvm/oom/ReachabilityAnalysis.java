package info.wufc.learning.java_basic.jvm.oom;

import java.lang.ref.PhantomReference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * 可达性分析 判断对象是否存活
 * @author fuchang.wu@foxmail.com
 * @since 2022-02-12 10:52
 */
public class ReachabilityAnalysis {
    private static final int _1Mb = 1024* 1024;

    static class Obj{
        private Object strongRef = new Byte[2* _1Mb];
        private SoftReference softRef = new SoftReference<Byte[]>(new Byte[2* _1Mb]);
        private WeakReference weakRef = new WeakReference<Byte[]>(new Byte[2* _1Mb]);
        // 没有指定注册队列，不会将PhantomReference对象放到队列中，
        // 只有注册了，才可以通过phantomRef.isEnqueued来知道Referent（里面的真实对象）即将被回收或者已经被回收
        private PhantomReference phantomRef = new PhantomReference(new Byte[2* _1Mb],null);
    }

    public static void main(String[] args) {
        Obj obj = new Obj();
        while(true){
            System.out.println("-----------");
            System.out.println(obj.strongRef);
            System.out.println(obj.softRef.get());
            System.out.println(obj.weakRef.get());
            // PhantomRef的get方法永远返回null
            System.out.println(obj.phantomRef.get());
        }
    }

}
