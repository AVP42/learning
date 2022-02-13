package info.wufc.learning.java_basic.jvm.oom;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 直接内存溢出
 *
 * @author fuchang.wu@foxmail.com
 * @since 2022-02-11 22:02
 */
public class DirectMemoryOOM {

    public static void main(String[] args) throws IllegalAccessException {
        Field field = Unsafe.class.getDeclaredFields()[0];
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe) field.get(null);
        while(true){
            unsafe.allocateMemory(1024 * 1024);
        }
    }



}
