package info.wufc.learning.design_pattern.singleton.destruct;

/**
 * @ClassName: LazySingleton
 * @Description: 利用双重检查机制
 * @Info: createdBy alien on 2018/4/2/002 21:47
 */
public class LazySingletonByDoubleCheck {
    private LazySingletonByDoubleCheck(){}

    // 没有使用valotile
    private static LazySingletonByDoubleCheck INSTANCE = null;

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
