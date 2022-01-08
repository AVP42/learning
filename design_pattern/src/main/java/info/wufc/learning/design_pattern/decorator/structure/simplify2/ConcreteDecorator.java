package info.wufc.learning.design_pattern.decorator.structure.simplify2;

/**
 * @ClassName: ConcreteDecorator
 * @Description: 具体装饰角色
 * @Info: createdBy alien on 2019/10/27/027 23:07
 */
public class ConcreteDecorator extends AbstractComponent {

    private AbstractComponent component;

    public ConcreteDecorator(AbstractComponent component) {
        this.component = component;
    }

    @Override
    public void doSomething() {
        System.out.println("ConcreteDecorator.doSomething");
        this.component.doSomething();
    }
}
