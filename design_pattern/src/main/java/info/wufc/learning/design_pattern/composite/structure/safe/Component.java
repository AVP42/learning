package info.wufc.learning.design_pattern.composite.structure.safe;

/**
 * @ClassName: Component
 * @Description: 抽象构件
 * @Info: createdBy alien on 2019/11/7/007 21:06
 */
public abstract class Component {
    /** 父对象*/
    protected Component parent;

    /** 抽象行为*/
    public abstract void doOperation();

    public Component getParent() {
        return parent;
    }

}
