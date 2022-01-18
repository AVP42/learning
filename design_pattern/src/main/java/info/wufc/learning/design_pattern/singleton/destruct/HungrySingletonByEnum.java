package info.wufc.learning.design_pattern.singleton.destruct;

import java.io.Serializable;

/**
 * 饿汉式-枚举
 * JAVA中保证枚举类中每一个枚举都是单例的
 *  不管通过反射还是序列化-反序列化都得到的是一个对象
 * @author fuchang.wu@foxmail.com
 * @since 2022-01-16 11:25
 */
public enum HungrySingletonByEnum implements Serializable {
    INSTANNCE;

    HungrySingletonByEnum() {
        System.out.println("HungrySingletonByEnum construct");
    }

    public void work() {
        System.out.println("HungryEnumSingleton.work");
    }
}
