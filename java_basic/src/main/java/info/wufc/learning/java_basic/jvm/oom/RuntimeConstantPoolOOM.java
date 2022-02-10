package info.wufc.learning.java_basic.jvm.oom;

/**
 * @author fuchang.wu@foxmail.com
 * @since 2022-02-10 22:19
 */
public class RuntimeConstantPoolOOM {
    public static void main(String[] args) {
        // StringBuilder.toString()会调用new String()，所以一定会在对上创建一个字符串对象。
        // JDK1.6时，首次使用str1.intern时会将str1代表的字符串复制到永久代的字符串常量池中，并返回复制后的实例，
        // 判断1：str1是StringBuilder创建的实例，在堆中，所以为false；
        // 判断2：false
        // 判断3：true
        // 判断4：同样也是false。

        // 从JDK1.7开始，intern的实现不再复制实例，而是在常量池中记录首次出现的实例引用，并且返回该引用。
        // 判断1：两者都是同一个引用，实例在堆中，所以为true
        // 判断2：str3是一个堆中新的对象，Str3.intern返回的是刚才Str1的实例，所以为false
        // 判断3： true
        // 判断4：java这个字符串在执行StringBuilder.toString()之前已经存在，字符串常量池中已经有它的引用，而str2，是在堆中

        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);

        String str3 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str3.intern() == str3);
        System.out.println(str3.intern() == str1);

        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);

    }

}
