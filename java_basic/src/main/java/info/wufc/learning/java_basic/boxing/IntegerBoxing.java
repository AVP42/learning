package info.wufc.learning.java_basic.boxing;

/**
 * @author fuchang.wu@foxmail.com
 * @since 2022-03-04 22:08
 */
public class IntegerBoxing {

    /**
     * 1. 包装类型的cache
     *      1.1 boolean true和false都缓存了
     *      1.2 Byte，Short，Integer, Long都是-128到127；Character是0-127
     *      1.3 通过jvm参数AutoBoxCacheMax只对Integer生效
     *      1.4 IntegerCache里面的还是Integer对象来的，并不是什么常量池的数据。
     * 2. 包装类在没有遇到算术运算的情况下是不会自动拆箱的
     *      装箱用Integer.valueOf()方法, 这种方式是可以使用缓存的，而
     *      拆箱用Integerd.intValue()方法
     * 3. equals方法不处理数据转型的关系
     * @param args
     */
    public static void main(String[] args) {
        Integer a = 1; // 这个会自动装箱，调用Integer.valueOf()方法，该方法会调用IntegerCache，不再缓存内，才会new出来。所以不建议使用new Integer()这种用法
        Integer a2 = new Integer(1); // 手动在heap创建一个Integer对象
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        int d2 = 3;
        byte d3 = 3;
        Integer e = 321;
        int f = 321;
        Long g = 3L; // 对应的是LongCache中值为3的Long对象
        Long h = 321L; // 对应的是LongCache中值为3的Long对象
        System.out.println(a == a2); // false
        System.out.println(c == d); // true
        System.out.println(e == f);// false
        System.out.println(c == (a + b)); // true
        System.out.println(c.equals(a + b)); // true
        System.out.println(g.equals(c)); // false ！！！误解 equals仅仅比较值, equals方法和普通类的equals方法一样，先判断类型是否是一样的，不是就是false了。
        System.out.println(g == d2); // true == 对比，不能是两个不同的包装类型，只要一个是基本类型，就可以比较，然后会转换成较大的那种类型进行比较
        System.out.println(h == f); // true == 对比，不能是两个不同的包装类型，只要一个是基本类型，就可以比较，然后会转换成较大的那种基本类型进行比较，也会进行自动拆箱
        System.out.println(g == (a + b)); // true 由于有算术运算，会自动拆箱，然后是与long比较，所以隐式转换为long类型 g == (long)(a + b); 然后会自动装箱。
        System.out.println(g.equals(a + b)); // false 通过自动拆箱相加，再自动装箱，a+b还是Integer类型

    }

}
