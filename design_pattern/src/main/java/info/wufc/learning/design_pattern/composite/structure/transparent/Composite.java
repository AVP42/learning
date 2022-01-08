package info.wufc.learning.design_pattern.composite.structure.transparent;


import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @ClassName: Composite
 * @Description: 树枝构件
 * @Info: createdBy alien on 2019/11/7/007 21:11
 */
public class Composite extends Component {
    private Collection<Component> children;



    @Override
    public void doOperation() {
        System.out.println("Composite.doOperation");
        if (!CollectionUtils.isEmpty(children)) {
            children.forEach(Component::doOperation);
        }
    }

    @Override
    public synchronized void add(Component child) {
        if (children == null) {
            children = new ArrayList<>();
        }
        children.add(child);
        // 设置该对象的父对象
        child.parent=this;
    }

    @Override
    public synchronized void remove(Component child) {
        if (children == null) {
            return;
        }
        boolean hasRemoved = children.remove(child);
        // 如果该子对象属于该父对象 即移除成功，则移除该对象的父对象
        if (hasRemoved) {
            child.parent = null;
        }
    }

    @Override
    public Collection<Component> getChildren() {
        return children;
    }

}
