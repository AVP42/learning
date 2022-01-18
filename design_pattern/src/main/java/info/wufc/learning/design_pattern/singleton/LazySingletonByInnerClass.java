package info.wufc.learning.design_pattern.singleton;

/**
 * @ClassName: LazyLoadSingleton
 * @Description: 利用内部类来实现懒加载模式
 * @Info: createdBy alien on 2018/10/7/007 12:16
 */

/**
 * 懒加载单例模式---调用时加载SinletonHolder，从而实例化
 * 因为JVM加载外部类时，并不会触发内部类的加载，所以实现延迟
 */
public class LazySingletonByInnerClass {
    private LazySingletonByInnerClass(){}

    private static class SingletonHolder{
       private final static LazySingletonByInnerClass instance = new LazySingletonByInnerClass();
    }

    public static LazySingletonByInnerClass getInStance() {
        return SingletonHolder.instance;
    }
}
