package info.wufc.learning.design_pattern.singleton;

/**
 * @ClassName: LazyLoadSingleton
 * @Description: 利用内部类来实现懒加载模式
 * @Info: createdBy alien on 2018/10/7/007 12:16
 */

/**
 * 懒加载单例模式---调用时加载SinletonHolder，从而实例化 因为内部类时不会在JVM启动时加载
 */
public class LazySingletonByInnerClass {
    private LazySingletonByInnerClass(){}

    static class SingletonHolder{
       static LazySingletonByInnerClass instance = new LazySingletonByInnerClass();
    }

    public static LazySingletonByInnerClass getInStance() {
        return SingletonHolder.instance;
    }
}
