package info.wufc.learning.design_pattern.visitor.structure;

import java.util.List;

/**
 * 对象结构角色
 *
 * @author fuchang.wu@foxmail.com
 * @since 2022-01-15 11:04
 */
public class ObjectStructure {

    private List<Node> nodes;

    public ObjectStructure(List<Node> nodes) {
        this.nodes = nodes;
    }

    /**
     * 实现遍历节点的操作
     * 实际上是对聚集中所有节点进行委派的过程
     * @param visitor
     */
    public void foreach(Visitor visitor){
        // 第一重动态分派  抽象node的accept方法 -> 具体node的accept方法
        nodes.forEach(node -> node.accept(visitor));
    }
}
