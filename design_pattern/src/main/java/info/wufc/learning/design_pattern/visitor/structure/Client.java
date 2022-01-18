package info.wufc.learning.design_pattern.visitor.structure;

import java.util.Arrays;

/**
 * 客户端
 *
 * @author fuchang.wu@foxmail.com
 * @since 2022-01-15 11:02
 */
public class Client {

    private static Visitor visitor;
    private static ObjectStructure objStructure;
    public static void main(String[] args) {
        // 具体使用哪个visitor 是由客户端决定的，也就是使用则决定的
        // 如果只有一种visitor，当然可以将这部分封装起来，但是这个不是visitor模式需要提供的东西了
        // 设计模式本身就是解决特定的问题，提供特定的松耦合。
        visitor = new ConcreteVisitor1();
        ConcreteNodeA concreteNodeA = new ConcreteNodeA();
        ConcreteNodeB concreteNodeB = new ConcreteNodeB();
        objStructure = new ObjectStructure(Arrays.asList(concreteNodeA, concreteNodeB));
        objStructure.foreach(visitor);


    }
}
