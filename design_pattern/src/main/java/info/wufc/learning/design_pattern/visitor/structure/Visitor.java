package info.wufc.learning.design_pattern.visitor.structure;

/**
 * 抽象访问者
 *
 * @author fuchang.wu@foxmail.com
 * @since 2022-01-15 10:53
 */
public abstract class Visitor {

    /**
     * 对节点A的访问操作
     *
     * 虽然NodeA和NodeB均继承了Node父类，
     * 但是我们希望在visit中实际调用node对象的方法，不是继承自Node父类的方法
     * 所以我们这里必须显式使用具体类型。
     */
    abstract  void visit(ConcreteNodeA node);

    abstract void visit(ConcreteNodeB node);

}
