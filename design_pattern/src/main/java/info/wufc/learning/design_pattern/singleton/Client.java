package info.wufc.learning.design_pattern.singleton;

import org.springframework.util.Assert;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;

/**
 * 客户端
 *
 * @author fuchang.wu@foxmail.com
 * @since 2022-01-16 11:43
 */
public class Client {
    public static void main(String[] args) throws Exception {
        testLazySingletonByInnerEnum();
    }

    static void testLazySingletonByInnerEnum() throws Exception {
        // 这种方式是不会resolveClass, 所以Holder枚举中的实例是不会初始化的，也就是说不会调用构造器。
//        Class<?> holderClassNotInitialized = LazySingletonByInnerEnum.Holder.class;
        System.out.println("----holder not resolved-----");
        // 使用class.forName 显式要求resolve class
//        Class<?> holderClass = Class.forName("info.wufc.learning.design_pattern.singleton.LazySingletonByInnerEnum$Holder", true, Client.class.getClassLoader());
//        System.out.println("----holder resolved because of class forName-----");
        // 调用了静态方法也会resolve, 所以为了避免提前触发，我们可以将enum 设置为private
//        LazySingletonByInnerEnum.Holder.doSomething();
        System.out.println("----holder resolved because of method invoke-----");
        LazySingletonByInnerEnum instance = LazySingletonByInnerEnum.getInstance();
        LazySingletonByInnerEnum instance2 = LazySingletonByInnerEnum.getInstance();

        // 反射创建LazySingletonByInnerEnum
        Constructor<LazySingletonByInnerEnum> constructor = LazySingletonByInnerEnum.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        LazySingletonByInnerEnum newInstance = constructor.newInstance();
        Assert.isTrue(newInstance != instance);
    }
}
