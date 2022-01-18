package info.wufc.learning.design_pattern.singleton;

/**
 * 饿汉式--内部枚举类
 *    这种只是将之前的内部静态类改成枚举类型，还是无法阻止反射创建LazySingletonByInnerEnum
 *
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
         * 如果作为静态属性，就和InnerClass没有区别
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
