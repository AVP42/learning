package info.wufc.learning.design_pattern.visitor.structure;

/**
 * 抽象节点角色
 *
 * @author fuchang.wu@foxmail.com
 * @since 2022-01-15 10:56
 */
public abstract class Node {

    abstract void accept(Visitor visitor);
}
