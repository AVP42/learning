package info.wufc.learning.design_pattern.singleton;

/**
 * 懒汉式-线程不安全
 *
 * @author fuchang.wu@foxmail.com
 * @since 2022-01-16 11:26
 */
public class LazySingleton {
    private static LazySingleton instance;

    private LazySingleton() {

    }

    public static LazySingleton getInstance() {
        if(instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
