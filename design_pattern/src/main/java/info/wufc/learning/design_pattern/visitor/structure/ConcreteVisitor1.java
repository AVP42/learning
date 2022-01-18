package info.wufc.learning.design_pattern.visitor.structure;

/**
 * 具体访问者
 *
 * @author fuchang.wu@foxmail.com
 * @since 2022-01-15 10:59
 */
public class ConcreteVisitor1 extends Visitor{

    @Override
    public void visit(ConcreteNodeA node) {
        System.out.println("ConcreteVisitor1.visit ConcreteNodeA");
        // 在这里回过头来调用遍历过程中，所希望调用的实际方法（商业方法）
        // 对于NodeA来说，就是operationA
        node.operationA();
    }

    @Override
    public void visit(ConcreteNodeB node) {
        System.out.println("ConcreteVisitor1.visit ConcreteNodeB");
        // 在这里回过头来调用遍历过程中，所希望调用的实际方法，对于NodeA来说，就是operationA
        node.operationB();

    }
}
