package info.wufc.learning.design_pattern.visitor.structure;

/**
 * 具体节点角色B
 *
 * @author fuchang.wu@foxmail.com
 * @since 2022-01-15 10:58
 */
public class ConcreteNodeB extends   Node{
    @Override
    public void accept(Visitor visitor) {
        System.out.println("ConcreteNodeB.accept");
        visitor.visit(this);
    }

    /**
     * 这是聚集遍历元素的时候希望执行的操作，不同具体类型，不同的操作方法
     */
    public void operationB() {
        System.out.println("ConcreteNodeB.operationB");
    }
}
