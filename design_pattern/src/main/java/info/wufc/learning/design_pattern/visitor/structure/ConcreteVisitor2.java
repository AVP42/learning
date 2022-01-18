package info.wufc.learning.design_pattern.visitor.structure;

/**
 * 具体访问者2
 *
 * @author fuchang.wu@foxmail.com
 * @since 2022-01-15 11:00
 */
public class ConcreteVisitor2 extends Visitor{

    @Override
    public void visit(ConcreteNodeA node) {
        System.out.println("ConcreteVisitor2.visit ConcreteNodeA");
    }

    @Override
    public void visit(ConcreteNodeB node) {
        System.out.println("ConcreteVisitor2.visit ConcreteNodeB");
    }
}
