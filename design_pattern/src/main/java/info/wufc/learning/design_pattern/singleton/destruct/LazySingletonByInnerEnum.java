package info.wufc.learning.design_pattern.singleton.destruct;

/**
 * 饿汉式--内部枚举类
 *    这种知识将之前的内部静态类改成枚举类型
 *    由于我们想要的不是内部类的实例
 * @author fuchang.wu@foxmail.com
 * @since 2022-01-16 11:32
 */
public class LazySingletonByInnerEnum {
    private LazySingletonByInnerEnum(){}

    public static LazySingletonByInnerEnum getInstance(){
        return Holder.INSTANCE.singleton;
    }

    private enum Holder{
        INSTANCE;

        /**
         * 作为成员属性
         * 如果作为静态属性，就和InnerClass没有区别，还是可以被反射创建
         */
        private final LazySingletonByInnerEnum singleton;

        Holder() {
            singleton = new LazySingletonByInnerEnum();
            System.out.println("LazySingletonByInnerEnum Holder construct");
        }

        public static void doSomething() {
            System.out.println("Holder.doSomething");
        }

    }

    public void work() {
        System.out.println("LazySingletonByInnerEnum.work");
    }

}
