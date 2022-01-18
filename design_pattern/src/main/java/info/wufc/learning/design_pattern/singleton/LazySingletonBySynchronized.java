package info.wufc.learning.design_pattern.singleton;

/**
 * 懒汉式-使用方法上的synchronized关键字
 *
 * @author fuchang.wu@foxmail.com
 * @since 2022-01-16 11:26
 */
public class LazySingletonBySynchronized {
    private static LazySingletonBySynchronized instance;

    private LazySingletonBySynchronized() {

    }

    public synchronized static LazySingletonBySynchronized getInstance() {
        if(instance == null) {
            instance = new LazySingletonBySynchronized();
        }
        return instance;
    }
}
