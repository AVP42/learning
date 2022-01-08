package info.wufc.learning.design_pattern.decorator.structure.simplify1;


/**
 * @ClassName: Decorator
 * @Description: 装饰角色 可以是抽象的
 * @Info: createdBy alien on 2019/10/27/027 23:03
 */
public abstract class Decorator extends ConcreteComponent {
    private ConcreteComponent component;

    public Decorator(ConcreteComponent component) {
        this.component = component;
    }

    public ConcreteComponent getComponent() {
        return component;
    }
}
