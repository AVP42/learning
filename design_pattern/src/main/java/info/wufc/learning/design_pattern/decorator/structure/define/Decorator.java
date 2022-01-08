package info.wufc.learning.design_pattern.decorator.structure.define;

/**
 * @ClassName: Decorator
 * @Description: 装饰角色 可以是抽象的
 * @Info: createdBy alien on 2019/10/27/027 23:03
 */
public class Decorator extends AbstractComponent{
    private AbstractComponent component;

    public Decorator(AbstractComponent component) {
        this.component = component;
    }

    public AbstractComponent getComponent() {
        return component;
    }


    @Override
    public void doSomething() {
        component.doSomething();
    }
}
