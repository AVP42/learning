package info.wufc.learning.design_pattern.visitor.structure;

/**
 * 具体节点角色A
 *
 * @author fuchang.wu@foxmail.com
 * @since 2022-01-15 10:57
 */
public class ConcreteNodeA extends Node {

    @Override
    public void accept(Visitor visitor) {
        System.out.println("ConcreteNodeA.accept");
        // 第二重动态分派 抽象visitor的visit方法 -> 具体visitor的visit方法
        visitor.visit(this);
    }

    /**
     * 这是聚集遍历元素的时候希望执行的操作，不同具体类型，不同的操作方法
     */
    public void operationA() {
        System.out.println("ConcreteNodeA.operationA");
    }
}
