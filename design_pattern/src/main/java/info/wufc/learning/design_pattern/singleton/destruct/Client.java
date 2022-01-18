package info.wufc.learning.design_pattern.singleton.destruct;

import org.springframework.util.Assert;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 客户端
 *
 * @author fuchang.wu@foxmail.com
 * @since 2022-01-16 11:43
 */
public class Client {
    public static void main(String[] args) throws Exception {
//        testHungrySingleton();
        testHungrySingletonByEnum();
//        testLazySingletonByDoubleCheck();
    }

    private static void testLazySingletonByDoubleCheck() {
        CountDownLatch latch = new CountDownLatch(900);
        ExecutorService pool = Executors.newFixedThreadPool(1000);
        for (int i = 0; i < 1000; i++) {
            pool.submit(() -> {
                try {
                    latch.countDown();
                    latch.await();
                } catch (Exception e) {
                }
                LazySingletonByDoubleCheck instance = LazySingletonByDoubleCheck.getInstance();
                if(instance == null){
                    System.out.println("null");
                }
            });
        }
    }

    private static void testHungrySingleton()throws Exception  {
        Class<HungrySingleton> clazz = HungrySingleton.class;
        System.out.println("-----");
        // 调用静态方法会触发静态属性的解析，从而生成对象。
        HungrySingleton.doSomething();
        System.out.println("-----");
        HungrySingleton instance = HungrySingleton.getInstance();

        // 如果implements Serializable
        // 注意，反序列化的时候已经不需要再调用私有构造器
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(bo);
        outputStream.writeObject(instance);

        ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
        ObjectInputStream inputStream = new ObjectInputStream(bi);
        Object o = inputStream.readObject();
        Assert.isTrue(o == instance);
    }

    private static void testHungrySingletonByEnum() throws Exception {
        // 尝试使用序列化后反序列化
        // 前提是实现了implements Serializable
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(bo);
        outputStream.writeObject(HungrySingletonByEnum.INSTANNCE);

        ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
        ObjectInputStream inputStream = new ObjectInputStream(bi);
        Object o = inputStream.readObject();
        // 得到的的确是同一个对象
        Assert.isTrue(HungrySingletonByEnum.INSTANNCE == o);


        // 尝试使用反射构造新的实例
        Class<HungrySingletonByEnum> clazz = HungrySingletonByEnum.class;
        // 获取构造器的时候会失败  会报 NoSuchMethodException
//        Constructor<HungrySingletonByEnum> constructor = clazz.getDeclaredConstructor();
//        constructor.setAccessible(true);
//        HungrySingletonByEnum newInstance = constructor.newInstance();
//        Assert.isTrue(newInstance == HungrySingletonByEnum.INSTANNCE);

        // 查看所有枚举的父类 java.lang.Enum 有默认的构造器 Enum(String name, int ordinal)
        // 通过反射构建，在newInstance方法中，有对枚举类型的检查，会抛出Cannot reflectively create enum objects错误
        Constructor<HungrySingletonByEnum> constructor2 = clazz.getDeclaredConstructor(String.class, int.class);
        constructor2.setAccessible(true);
        HungrySingletonByEnum newInstance2 = constructor2.newInstance("INSTANCE2", 1);
        Assert.isTrue(newInstance2 == HungrySingletonByEnum.INSTANNCE);
    }

}
