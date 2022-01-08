package info.wufc.learning.design_pattern.composite.structure.transparent;

import java.util.Collection;

/**
 * @ClassName: Leaf
 * @Description: 树叶构件
 * @Info: createdBy alien on 2019/11/7/007 21:10
 */
public class Leaf extends Component {

    @Override
    public void doOperation() {
        System.out.println("Leaf.doOperation_"+ Math.random());
    }

    @Override
    public void add(Component child) {
        throw new RuntimeException("not supported");
    }

    @Override
    public void remove(Component child) {
        throw new RuntimeException("not supported");
    }

    @Override
    public Collection<Component> getChildren() {
        return null;
    }
}
