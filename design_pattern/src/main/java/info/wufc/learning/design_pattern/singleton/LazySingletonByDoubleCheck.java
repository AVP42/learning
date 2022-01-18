package info.wufc.learning.design_pattern.singleton;

/**
 * @ClassName: LazySingleton
 * @Description: 利用双重检查机制
 * @Info: createdBy alien on 2018/4/2/002 21:47
 */
/**
 *  使用双重检查机制，必须对需要获取的单例对象使用volatile修饰，使得线程不会使用重排序功能
 *  否则由于 INSTANCE = new LazySingletonByDoubleCheck();包含的操作
 *      1.memory = allocate() // 分配对象的内存空间
 *      2.ctorInstance(memory) // 初始化对象
 *      3.sInstance = memory // 赋值操作
 *   因为java规范中的规范 允许单线程内不改变线程执行结果的重排序，所以2和3步操作可能重排序为先3后2；
 *   这时如果B线程在A线程刚执行完3的时候，判断到变量不为空，随机返回，但是对象还没真正初始化,
 *   导致拿到可能是不完整的对象。
 *
 *  而使用了volatile之后，volatile提供了两个功能，保证线程可见性以及提供一定的有序性。
 *  这里主要利用了volatile提供的一定程序的有序性来实现的
 *  当程序执行到volatile变量的读或者写操作时，前面操作的更改肯定全部完成，而且结果对后面的操作课件；后面的操作保证还没有进行
 *
 *  所以3不会被排在2的前面
 */
public class LazySingletonByDoubleCheck {
    private LazySingletonByDoubleCheck(){}

    private static volatile LazySingletonByDoubleCheck INSTANCE = null;

    public static LazySingletonByDoubleCheck getInstance() {
        if (INSTANCE == null) {
            synchronized (LazySingletonByDoubleCheck.class) {
                /*双重检查机制*/
                if (INSTANCE == null) {
                    INSTANCE = new LazySingletonByDoubleCheck();

                }
            }
        }
        return INSTANCE;
    }

    public void work() {
        System.out.println("HungrySingleton.work");
    }
}
