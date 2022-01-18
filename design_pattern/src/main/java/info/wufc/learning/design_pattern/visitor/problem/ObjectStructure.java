package info.wufc.learning.design_pattern.visitor.problem;

import info.wufc.learning.design_pattern.visitor.structure.ConcreteNodeA;
import info.wufc.learning.design_pattern.visitor.structure.ConcreteNodeB;
import info.wufc.learning.design_pattern.visitor.structure.Node;

import java.util.List;

/**
 * 不使用访问者模式的聚集
 *
 * @author fuchang.wu@foxmail.com
 * @since 2022-01-15 11:13
 */
public class ObjectStructure {

    private List<Node> nodes;

    public ObjectStructure(List<Node> nodes) {
        this.nodes = nodes;
    }

    /**
     * 聚集遍历的时候希望每个具体类型的operationXXX()方法
     */
    public void foreach(){
        for (Node node : nodes) {
            // 因为要调用不同类型的不同方法，这个方法不是由父类Node声明的
            // 相当于在调用
            if(node instanceof ConcreteNodeA){
                ((ConcreteNodeA) node).operationA();
            }
            else if(node instanceof ConcreteNodeB){
                ((ConcreteNodeB)node).operationB();
            }
        }
    }
}
